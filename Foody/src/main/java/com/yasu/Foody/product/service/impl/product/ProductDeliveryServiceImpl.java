package com.yasu.Foody.product.service.impl.product;

import com.yasu.Foody.product.service.product.ProductDeliveryService;

import org.springframework.stereotype.Service;



@Service
public class ProductDeliveryServiceImpl implements ProductDeliveryService {
    @Override
    public String getDeliveryInfo(Long productId) {
        return "Tomorrow";
    }
}
