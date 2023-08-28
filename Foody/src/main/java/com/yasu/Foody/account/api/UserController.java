package com.yasu.Foody.account.api;


import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.model.UserSaveReq;

import com.yasu.Foody.account.repository.UserRepository;


import com.yasu.Foody.account.security.dto.AuthRequest;
import com.yasu.Foody.account.security.dto.AuthResponse;
import com.yasu.Foody.account.security.jwt.JWTUtil;


import com.yasu.Foody.account.service.UserService;

import com.yasu.Foody.account.security.errors.AlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)

public class UserController {

    private final UserService usersService;

    @Autowired
    UserRepository userRepository;


    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsService userService;

    @PostMapping("/create")
    public Mono<ResponseEntity<?>> createUser( @Valid @RequestBody UserSaveReq entity){
        return usersService.createUser(entity)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).build());


    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
        return userService.findByUsername(authRequest.getUsername())
                .filter(userDetails ->
                        passwordEncoder.matches(
                                authRequest.getPassword(),
                                userDetails.getPassword()
                        )
                ).map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(UNAUTHORIZED).build()));
    }



    @GetMapping("/list")
    public Flux<UserEntity> getAllUsers(){
    return usersService.findAll();
    }

}


