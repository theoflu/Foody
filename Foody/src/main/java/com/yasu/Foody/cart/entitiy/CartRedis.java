package com.yasu.Foody.cart.entitiy;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@RedisHash("cart")
@Data
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRedis implements Serializable {
    @Id
    private Long id;
    private HashMap<String,Integer> productCC; //CodeAndCount
    @Indexed
    private String username;
    private int count;
    private Date createdTime;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}
