package com.yasu.Foody.account.entity.model;

import com.yasu.Foody.account.entity.roles.ERole;
import lombok.Data;

import java.util.List;

@Data
public class AssignRoleReq {
    Long id ;
    List<ERole> roles;
}
