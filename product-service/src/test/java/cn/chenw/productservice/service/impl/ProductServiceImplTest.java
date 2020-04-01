package cn.chenw.productservice.service.impl;

import cn.chenw.commonservice.model.BaseModel;
import cn.chenw.commonservice.model.poto.Product;
import cn.chenw.productservice.service.ProductService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
/**
 * @author  chenw
 * @date  2020/4/1 14:32
 * 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {


    @Autowired
    ProductService productService;

    @Test
    public void queryProductById() {
        BaseModel baseModel = productService.queryProductById(new Product().setPid(1));
        System.out.println("打印:"+ JSON.toJSONString(baseModel));
    }

    @Test
    public void updateProductStock() {
    }
}