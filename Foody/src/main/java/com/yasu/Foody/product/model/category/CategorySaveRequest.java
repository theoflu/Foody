package com.yasu.Foody.product.model.category;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class CategorySaveRequest {
    private String id;
    private String name;
}
