package com.yasu.Foody.product.service.impl.product;

import com.yasu.Foody.product.service.product.ProductAmountService;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductAmountServiceImpl implements ProductAmountService {
    @Override
    public int getByProductId(UUID productId) {
        return 5;
    }

}
