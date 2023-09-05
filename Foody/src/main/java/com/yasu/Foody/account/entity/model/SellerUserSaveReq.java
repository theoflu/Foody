package com.yasu.Foody.account.entity.model;

import com.yasu.Foody.account.entity.roles.ERole;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SellerUserSaveReq {

    private String id;
    private String userName;
    private String email;
    private String password;
    private String userType;
    private String cellphone;
    private String address;
    private String addressName;
    private List<ERole> roles;

    private Long userId;
    private String vergiNo;
    private String sellerName;
    private String sellerAddress;
}
