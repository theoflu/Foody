package com.yasu.Foody.product.model;

import lombok.Builder;
import lombok.Data;




@Data
@Builder
public class ProductSellerResponse
{
    private String name;
    private Long id;
}
