package com.yasu.Foody.cart.api;

import com.yasu.Foody.account.entity.model.UserSaveReq;
import com.yasu.Foody.cart.entitiy.model.CartReq;
import com.yasu.Foody.cart.response.CartResponse;
import com.yasu.Foody.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
public class CartController {
    private final CartService cartService;
    @PostMapping("/save")
    public Mono<ResponseEntity<CartResponse>> save(@RequestBody CartReq cartReq) {
        return cartService.save(cartReq)
                .map(cartResponse -> ResponseEntity.ok(cartResponse))
                .onErrorReturn(ResponseEntity.notFound().build());
    }

}
