package com.yasu.Foody.account.service;

import com.yasu.Foody.account.dto.LoginDto;
import com.yasu.Foody.account.dto.UserDto;
import com.yasu.Foody.account.entity.AddressEntity;
import com.yasu.Foody.account.entity.EsVerificationCode;
import com.yasu.Foody.account.entity.SellerUserEntity;
import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.model.*;
import com.yasu.Foody.account.entity.roles.Role;
import com.yasu.Foody.security.dto.Message;
import com.yasu.Foody.security.model.User;
import com.yasu.Foody.security.response.LoginMesage;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<AddressEntity> createUser(UserSaveReq userSaveReq);

    Mono<UserEntity> findUserByEmail(String username);
    Mono<SellerUserEntity> findUserById(Long id);

    UserEntity findUserBy(String username);

    LoginMesage login(LoginDto loginDto);

    Flux<UserEntity> findAll();
    Mono<Role> roleAdd(Role eRole);
    Mono<UserDto>  assignRole(AssignRoleReq req);
    Mono<Message>  userDelete(UserDeleteReq req);
    Mono<Message> activationCode(UserActivateReq userActivateReq);
    Mono<UserEntity> verificationCode(EsVerificationCode esVerificationCode);
    Mono<UserEntity> UpdatePassword(UpdatePassReq updatePassReq);
    Mono<?> ForgetPassword(UpdatePassReq updatePassReq);
    Mono<Message> sellerNameChecker(String sellername);
}

