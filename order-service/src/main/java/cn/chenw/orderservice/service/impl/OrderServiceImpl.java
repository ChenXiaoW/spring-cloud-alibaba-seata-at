package cn.chenw.orderservice.service.impl;

import cn.chenw.commonservice.model.BaseModel;
import cn.chenw.commonservice.model.dto.CreateOrderDTO;
import cn.chenw.commonservice.model.poto.Order;
import cn.chenw.commonservice.model.poto.Product;
import cn.chenw.commonservice.util.CodeConstant;
import cn.chenw.orderservice.dao.OrderDao;
import cn.chenw.orderservice.manager.ProductFeignManager;
import cn.chenw.orderservice.service.OrderService;
import com.alibaba.fastjson.JSON;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author  chenw
 * @date  2020/4/1 11:07
 *
 * 订单业务
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductFeignManager productFeignManager;

    @Autowired
    OrderDao orderDao;

    /**
     * 创建订单
     *
     * @param createOrderDTO (pid-商品ID,count-数量)
     * @return
     */
    @GlobalTransactional //开启全局事务
    @Override
    public BaseModel insertOrder(CreateOrderDTO createOrderDTO) {
        BaseModel baseModel = null;
        //查询商品
        baseModel = productFeignManager.queryProductById(new Product().setPid(createOrderDTO.getPid()));
        if(CodeConstant.FAIL.equals(baseModel.getResult())){
            return baseModel;
        }
        Product product = JSON.parseObject(JSON.toJSONString(baseModel.getData()), Product.class);
        if(product.getStock()<createOrderDTO.getCount()){
            return new BaseModel(CodeConstant.UPDATE_ERROR,CodeConstant.FAIL,"生成订单失败，库存不足",null);
        }
        Order order = new Order();
        BeanUtils.copyProperties(createOrderDTO,order);
        //总价计算
        BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(order.getCount()));
        order.setTotalprice(totalPrice);
        order.setPname(product.getPname());
        Integer integer = orderDao.insertOrder(order);
        if(integer == 0){
            return new BaseModel(CodeConstant.UPDATE_ERROR,CodeConstant.FAIL,"生成订单失败",null);
        }
        //库存扣减
       Integer number =  product.getStock()-order.getCount();
        product.setStock(number);
        baseModel = productFeignManager.updateProductStock(product);
        if(CodeConstant.FAIL.equals(baseModel.getResult())){
            return new BaseModel(CodeConstant.UPDATE_ERROR,CodeConstant.FAIL,"生成订单失败",null);
        }
        return new BaseModel(CodeConstant.SUCCESS_CODE,CodeConstant.SUCCESS,"下单成功",null);
    }
}
