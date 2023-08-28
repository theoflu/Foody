package com.yasu.Foody.account.service.impl;

import com.yasu.Foody.account.dto.LoginDto;
import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.model.UserSaveReq;
import com.yasu.Foody.account.entity.roles.ERole;

import com.yasu.Foody.account.repository.RoleRepository;
import com.yasu.Foody.account.repository.UserRepository;

import com.yasu.Foody.account.response.LoginMesage;


import com.yasu.Foody.account.security.errors.AlreadyExistsException;
import com.yasu.Foody.account.security.model.User;
import com.yasu.Foody.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.rmi.AlreadyBoundException;
import java.util.*;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public Mono<UserEntity> createUser(UserSaveReq userSaveReq){

                String c = this.passwordEncoder.encode(userSaveReq.getPassword());
        UserEntity userEntity = UserEntity.builder().id(userSaveReq.getId())
                .userName(userSaveReq.getUserName())
                .email(userSaveReq.getEmail())
                .password(c)
                .address(userSaveReq.getAddress())
                .cellphone(userSaveReq.getCellphone())
                .enabled(true)
                .roles(List.of(ERole.ROLE_USER))
                .build();
        Mono<UserEntity> a= userRepository.findUserByEmail(userSaveReq.getEmail())
                .flatMap(__->Mono.error(new AlreadyBoundException("Dayı sen burdasın aq")))
                .switchIfEmpty(Mono.defer(()->userRepository.save(userEntity)))
                .cast(UserEntity.class);

                return a;






    //    return Mono.error(new AlreadyBoundException("Dayı sen burdasın aq"));




    }

    @Override
    public Mono<UserEntity> findUserByEmail(String Email) {
        return userRepository.findUserByEmail(Email);
    }

    @Override
    public LoginMesage login(LoginDto loginDto) {
      /*
        String msg="";
       UserEntity dt=(userRepository.findByEmail(loginDto.getEmail()).block());
        if (dt!=null){
            String pass=loginDto.getPassword();
            String encodedPassword=dt.getPassword();
            Boolean isPsdRight=passwordEncoder.matches(pass,encodedPassword);
            if(isPsdRight){
                Optional<UserEntity> usr=userRepository.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
                if(usr.isPresent())
                    return new LoginMesage("Login Success",true);
                else
                    return new LoginMesage("Login Failed",false);
            }
            else
                return new LoginMesage("Password Not Match!",false);

        }
        return new LoginMesage("Email not exist",false);
*/
        return null;

    }



    @Override
    public Flux<UserEntity> findAll() {

        return userRepository.findAll();
    }



}
