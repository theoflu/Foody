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
@Data
@Document(indexName = "cart")
@EqualsAndHashCode(of="id")
@Builder
public class CartEs {
    @Id
    private String id;
    private UserEntity userEntity;
    private Product product;
    private Category category;
    private Date createdTime;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}
