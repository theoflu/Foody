package com.yasu.Foody.product.domain;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Document(collection="product")
@Data
@EqualsAndHashCode(of="id")
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private  String productCode;//name+model
    private  String description;
    private  String companyID;
    private  String features;
    private LinkedHashMap<moneyType ,BigDecimal> Price;
    private  String categoryId;
    private List<ProductImage> productImage;
    private Boolean active;

}
