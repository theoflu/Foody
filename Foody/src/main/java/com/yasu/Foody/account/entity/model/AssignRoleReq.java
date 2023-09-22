package com.yasu.Foody.account.entity.model;

import com.yasu.Foody.account.entity.roles.ERole;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AssignRoleReq {
    private Long id ;
    private List<ERole> roles;
}
