package com.yasu.Foody.account.service;

import com.yasu.Foody.account.dto.LoginDto;
import com.yasu.Foody.account.dto.UserDto;
import com.yasu.Foody.account.entity.AddressEntity;
import com.yasu.Foody.account.entity.SellerUserEntity;
import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.model.AssignRoleReq;
import com.yasu.Foody.account.entity.model.UserSaveReq;
import com.yasu.Foody.account.entity.roles.ERole;
import com.yasu.Foody.account.entity.roles.Role;
import com.yasu.Foody.security.response.LoginMesage;

import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface UserService {
    Mono<AddressEntity> createUser(UserSaveReq userSaveReq);

    Mono<UserEntity> findUserByEmail(String username);
    Mono<SellerUserEntity> findUserById(Long id);

    UserEntity findUserBy(String username);

    LoginMesage login(LoginDto loginDto);

    Flux<UserEntity> findAll();
    Mono<Role> roleAdd(Role eRole);
    Mono<UserDto>  assignRole(AssignRoleReq req);
}

