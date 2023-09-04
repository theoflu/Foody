package com.yasu.Foody.cart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDto {
    private String username;
    private String email;
    private String cellphone;
    private String address;
    private String productName;
    private  String productCode;//name+model
    private  String companyID;
}
