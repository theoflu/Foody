package com.yasu.Foody.product.domain;


import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


@Document(collection="product")
@Data
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private Long id;
    private String name;
    private  String productCode;//name+model
    private  String description;
    private  Long companyID;//companyId'im sellerUser'daki id olacak o kullanıcı company olacak
    //
    private  String features;
    private LinkedHashMap<moneyType ,BigDecimal> Price;
    private  String categoryId;
    private List<ProductImage> productImage;
    private int productStock;
    private Boolean active;

}
