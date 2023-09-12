package com.yasu.Foody.product.model.product;

import lombok.Builder;
import lombok.Data;




@Data
@Builder
public class UpdateProductActive {
    private Long id;
    private boolean active;
}
