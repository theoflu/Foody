package com.yasu.Foody.account.entity;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document(collection="sellerUser")
@Data
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerUserEntity {
    @Id
    private UUID id;
    private UUID userId;
    private String vergiNo;
    private String sellerName;
    private String sellerAddress;

}
