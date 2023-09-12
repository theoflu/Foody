package com.yasu.Foody.cart.response;

import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.category.Category;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

import org.springframework.data.annotation.Id;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class CartResponse {
    private UUID id;
    private UUID userId;
    private UUID  productId;
    private Category category;
    private Date createdTime;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}
