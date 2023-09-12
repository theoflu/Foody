package com.yasu.Foody.product.model.product;

import com.yasu.Foody.product.model.ProductSellerResponse;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ProductDetailResponse {
    private UUID id;
    private List<String> images;
    private  String productCode;//name+model
    private String name;
    private String description;
    private ProductSellerResponse seller;
    private String features;
    private int available;
    private boolean freeDelivery;
    private String deliveryIn;
    private BigDecimal price;
    private String moneySymbol;
    private UUID categoryId;
    private int productStock;

}