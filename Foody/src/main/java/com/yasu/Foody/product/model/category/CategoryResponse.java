package com.yasu.Foody.product.model.category;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Builder
@Data
public class CategoryResponse {

    private String id;
    private String name;
}
