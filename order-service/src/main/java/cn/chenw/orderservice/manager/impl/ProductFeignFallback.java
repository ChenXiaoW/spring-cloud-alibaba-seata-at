package cn.chenw.orderservice.manager.impl;

import cn.chenw.commonservice.model.BaseModel;
import cn.chenw.commonservice.model.poto.Product;
import cn.chenw.commonservice.util.CodeConstant;
import cn.chenw.orderservice.manager.ProductFeignManager;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author  chenw
 * @date  2020/4/1 14:52
 *
 * 容错类
 */
@Component
public class ProductFeignFallback implements FallbackFactory<ProductFeignManager> {
    @Override
    public ProductFeignManager create(Throwable throwable) {
        return new ProductFeignManager() {
            @Override
            public BaseModel queryProductById(Product product) {
                return new BaseModel(CodeConstant.QUERY_ERROR,CodeConstant.FAIL,throwable.getMessage(),null);
            }

            @Override
            public BaseModel updateProductStock(Product product) {
                return new BaseModel(CodeConstant.UPDATE_ERROR,CodeConstant.FAIL,throwable.getMessage(),null);
            }
        };
    }
}
