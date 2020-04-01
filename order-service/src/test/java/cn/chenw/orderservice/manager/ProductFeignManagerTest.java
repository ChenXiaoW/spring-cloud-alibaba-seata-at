package cn.chenw.orderservice.manager;

import cn.chenw.commonservice.model.BaseModel;
import cn.chenw.commonservice.model.poto.Product;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author  chenw
 * @date  2020/4/1 15:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductFeignManagerTest {

    @Autowired
    ProductFeignManager productFeignManager;

    @Test
    public void queryProductById() {
        BaseModel baseModel = productFeignManager.queryProductById(new Product().setPid(1));
        System.out.println("打印:"+ JSON.toJSONString(baseModel));
    }

    @Test
    public void updateProductStock() {
    }
}