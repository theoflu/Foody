package com.yasu.Foody.product.model.product;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import java.util.LinkedHashMap;
import java.util.List;

import com.yasu.Foody.product.domain.moneyType;


@Data
@Builder


public class ProductSaveRequest {
    private String id;
    private String name;
    private  String productCode;//name+model
    private String description;
    private String features;
    private int available;
    private LinkedHashMap<moneyType ,BigDecimal> Price;
    private List<String> images;
    private String sellerId;
    private String categoryId;
    private int productStock;

}
