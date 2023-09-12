package com.yasu.Foody.product.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class ProductSellerResponse
{
    private String name;
    private UUID id;
}
