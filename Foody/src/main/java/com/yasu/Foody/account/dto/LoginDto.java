package com.yasu.Foody.account.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class LoginDto {

    private String username;
    private String password;

}
