package cn.chenw.orderservice.service;

import cn.chenw.commonservice.model.BaseModel;
import cn.chenw.commonservice.model.dto.CreateOrderDTO;
import cn.chenw.commonservice.model.poto.Order;

/**
 * @author  chenw
 * @date  2020/4/1 11:03
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @param createOrderDTO (pid-商品ID,count-数量)
     * @return
     */
    BaseModel insertOrder(CreateOrderDTO createOrderDTO);
}
