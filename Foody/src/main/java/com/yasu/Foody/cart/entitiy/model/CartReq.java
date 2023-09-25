package com.yasu.Foody.cart.entitiy.model;

import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.category.Category;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;


import java.util.Date;


@Data
@Builder
public class CartReq {
    private Long id;
    private String productCode;
    private String username;
    private int count;
}
