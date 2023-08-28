package com.yasu.Foody.product.model.product;

import com.yasu.Foody.product.domain.es.CompanyEs;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.yasu.Foody.product.domain.moneyType;

@Data
@Builder
public class ProductSaveRequest {
    private String id;
    private String name;
    private String description;
    private String features;
    private BigDecimal available;
    private LinkedHashMap<moneyType ,BigDecimal> Price;
    private List<String> images;
    private String sellerId;
    private String categoryId;

}
