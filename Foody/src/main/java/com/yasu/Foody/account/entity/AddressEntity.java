package com.yasu.Foody.account.entity;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection="address")
@Data
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    @Id
    private Long id;
    private Long UserId;
    private String AddressName;
    private String Address;



}
