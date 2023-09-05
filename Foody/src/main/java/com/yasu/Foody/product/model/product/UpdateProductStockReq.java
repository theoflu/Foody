package com.yasu.Foody.product.model.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProductStockReq {
    private  String productCode;

    private int stock;
}
