package com.yasu.Foody.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpResponse {

    //    private boolean success;
    private String userId;
    private String token;
    private String secretKey;

}