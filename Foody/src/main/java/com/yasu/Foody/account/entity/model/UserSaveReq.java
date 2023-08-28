package com.yasu.Foody.account.entity.model;

import com.yasu.Foody.account.entity.roles.ERole;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserSaveReq {
    private String id;
    @Getter
    private String userName;
    @Getter
    private String email;
    @Getter
    private String password;

    private String cellphone;
    private String address;

    private List<ERole> roles;

}
