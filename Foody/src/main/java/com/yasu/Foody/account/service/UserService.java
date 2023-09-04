package com.yasu.Foody.account.service;

import com.yasu.Foody.account.dto.LoginDto;
import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.model.UserSaveReq;
import com.yasu.Foody.account.response.JwtResponse;
import com.yasu.Foody.account.response.LoginMesage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface UserService {
Mono<UserEntity> createUser(UserSaveReq userSaveReq);
Mono<UserEntity> findUserByEmail(String username);
UserEntity findUserBy(String username);
LoginMesage login(LoginDto loginDto);
Flux<UserEntity> findAll ();
}
