package com.yasu.Foody.cart.api;

import com.yasu.Foody.account.entity.model.UserSaveReq;
import com.yasu.Foody.cart.entitiy.CartRedis;
import com.yasu.Foody.cart.entitiy.model.CartReq;
import com.yasu.Foody.cart.response.CartResponse;
import com.yasu.Foody.cart.service.CartService;
import com.yasu.Foody.security.dto.Message;
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
    @PostMapping("/purchase")
    public Mono<ResponseEntity<String>> purchase(@RequestBody CartRedis cartReq) {
        return cartService.purchase(cartReq)
                .thenReturn(ResponseEntity.ok("Satın alma işlemi başarıyla tamamlandı."));
    }
    @PostMapping("/saves")
    public ResponseEntity<Mono<?>> cart(@RequestBody CartReq cartReq) {
        return ResponseEntity.ok(cartService.cart(cartReq));
    }

}
