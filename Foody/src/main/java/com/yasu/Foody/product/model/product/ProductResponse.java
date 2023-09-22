package com.yasu.Foody.product.model.product;

import com.yasu.Foody.product.model.ProductSellerResponse;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;



@Data
@Builder
public class ProductResponse {
    private Long id;
    private String image;
    private String name;
    private  String productCode;//name+model
    private  String description;
    private ProductSellerResponse seller;
    private  String features;
    private  int available;
    private  boolean freeDelivery;
    private  String deliveryIn;
    private BigDecimal price;
    private String moneySymbol;
    private String categoryId;
    private int productStock;





}
