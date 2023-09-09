package com.yasu.Foody.account.api;


import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.model.UserSaveReq;

import com.yasu.Foody.account.repository.UserRepository;


import com.yasu.Foody.product.model.product.UpdateProductActive;
import com.yasu.Foody.security.dto.AuthResponse;
import com.yasu.Foody.security.jwt.JWTUtil;


import com.yasu.Foody.account.service.UserService;

import com.yasu.Foody.product.model.product.ProductSaveRequest;
import com.yasu.Foody.product.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")

public class UserController {

    private final UserService usersService;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;
    @PostMapping("/create")
    public Mono<ResponseEntity<?>> createUser( @Valid @RequestBody UserSaveReq entity){
        return usersService.createUser(entity)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).build())
                ;


    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> loginUser( @RequestBody UserEntity loginDto){

        return usersService.findUserByEmail(loginDto.getUsername())
                .filter(userDetails ->
                        passwordEncoder.matches(
                                loginDto.getPassword(),
                                userDetails.getPassword()
                        )
                ).map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails),userDetails)))
                .switchIfEmpty(Mono.just(ResponseEntity.status(NOT_FOUND).build()));



    }
    /*
    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
        return userService.findByUsername(authRequest.getUsername())
                .filter(userDetails ->
                        passwordEncoder.matches(
                                authRequest.getPassword(),
                                userDetails.getPassword()
                        )
                ).map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails),userDetails.getUsername())))
                .switchIfEmpty(Mono.just(ResponseEntity.status(UNAUTHORIZED).build()));
    }


     */

    @PostMapping("/product/add")
    public  ResponseEntity<?> seller(@Valid @RequestBody ProductSaveRequest productSaveRequest){


        return ResponseEntity.ok(  productService.save(productSaveRequest));
    }
    @PostMapping("/product/delete")
    public  ResponseEntity<?> del(@Valid @RequestBody UpdateProductActive id){


        return ResponseEntity.ok(  productService.deleteProduct(id));
    }


    @GetMapping("/list")
    public Flux<UserEntity> getAllUsers(){
    return usersService.findAll();
    }

}


