package com.yasu.Foody.account.entity.model;

import lombok.Data;

@Data
public class UpdatePassReq {
    private Long id;
    private String password;
    private String code;
}
