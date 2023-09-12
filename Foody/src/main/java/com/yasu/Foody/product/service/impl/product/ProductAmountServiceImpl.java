package com.yasu.Foody.product.service.impl.product;

import com.yasu.Foody.product.service.product.ProductAmountService;

import org.springframework.stereotype.Service;



@Service
public class ProductAmountServiceImpl implements ProductAmountService {
    @Override
    public int getByProductId(Long productId) {
        return 5;
    }

}
