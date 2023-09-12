package com.yasu.Foody.product.model.product;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class UpdateProductActive {
    private UUID id;
    private boolean active;
}
