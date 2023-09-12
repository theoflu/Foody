package com.yasu.Foody.account.service;

import com.yasu.Foody.account.dto.LoginDto;
import com.yasu.Foody.account.entity.AddressEntity;
import com.yasu.Foody.account.entity.SellerUserEntity;
import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.model.UserSaveReq;
import com.yasu.Foody.security.response.LoginMesage;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
    Mono<AddressEntity> createUser(UserSaveReq userSaveReq);

    Mono<UserEntity> findUserByEmail(String username);
    Mono<SellerUserEntity> findUserById(Long id);

    UserEntity findUserBy(String username);

    LoginMesage login(LoginDto loginDto);

    Flux<UserEntity> findAll();
}

