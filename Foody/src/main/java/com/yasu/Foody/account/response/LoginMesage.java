package com.yasu.Foody.account.response;

import lombok.Data;

@Data
public class LoginMesage {
    String message;
    Boolean status;

    public LoginMesage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
    public LoginMesage(String message) {
        this.message = message;
    }
}
