package com.yasu.Foody.product.model.product;

import com.yasu.Foody.product.model.ProductSellerResponse;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
public class ProductDetailResponse {
    private Long id;
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
    private Long categoryId;
    private int productStock;

}