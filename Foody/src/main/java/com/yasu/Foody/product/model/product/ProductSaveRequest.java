package com.yasu.Foody.product.model.product;


import com.mongodb.lang.Nullable;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import com.yasu.Foody.product.domain.moneyType;



@Data
@Builder


public class ProductSaveRequest {
    private UUID id;
    private String name;
    private  String productCode;//name+model
    private String description;
    private String features;
    private int available;
    private LinkedHashMap<moneyType ,BigDecimal> Price;
    private List<String> images;
    private UUID sellerId;
    private String categoryId;
    private int productStock;

}
