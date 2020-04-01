package cn.chenw.orderservice.dao;

import cn.chenw.commonservice.model.poto.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author  chenw
 * @date  2020/4/1 13:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {

    @Autowired
    OrderDao orderDao;

    @Test
    public void insertOrder() {
        Order order = new Order().setCount(10).setPid(1).setPname("商品").setTotalprice(new BigDecimal(100));
        Integer integer = orderDao.insertOrder(order);
        System.out.println("打印:"+integer);
    }
}