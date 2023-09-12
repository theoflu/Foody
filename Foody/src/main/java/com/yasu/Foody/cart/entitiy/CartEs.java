package com.yasu.Foody.cart.entitiy;

import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.category.Category;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Data
@Document(indexName = "cart")
@EqualsAndHashCode(of="id")
@Builder
public class CartEs {
    @Id
    private UUID id;
    private UUID userId;
    private UUID productId;
    private UUID categoryId;
    private Date createdTime;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}
