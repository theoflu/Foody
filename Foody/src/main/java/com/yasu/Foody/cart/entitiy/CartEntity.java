package com.yasu.Foody.cart.entitiy;

import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.category.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;

import java.util.Date;

@Document(collection="cart")
@Data
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartEntity {
    @Id
    private String id;
    private String userId;
    private String productId;
    private Date createdTime;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}
