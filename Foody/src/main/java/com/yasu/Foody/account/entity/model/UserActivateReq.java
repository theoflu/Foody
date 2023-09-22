package com.yasu.Foody.account.entity.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserActivateReq {
    private String email;
    private String password;
}
