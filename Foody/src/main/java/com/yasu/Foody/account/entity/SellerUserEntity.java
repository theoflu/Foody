package com.yasu.Foody.account.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="sellerUser")
@Data
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerUserEntity {
    private String id;
    private String userId;
    private String vergiNo;
    private String sellerName;
    private String sellerAddress;

}
