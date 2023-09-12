package com.yasu.Foody.account.entity.model;

import com.yasu.Foody.account.entity.roles.ERole;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForReadableInstant;


import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class UserSaveReq {
    private UUID id;
    private String userName;
    private String email;
    private String password;
    private String userType;
    private String cellphone;
    private String address;
    private String addressName;
    private List<ERole> roles;

    private UUID userId;
    private String vergiNo;
    private String sellerName;
    private String sellerAddress;


}
