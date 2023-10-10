package com.yasu.Foody.cart.service;

import com.yasu.Foody.cart.entitiy.CartRedis;
import com.yasu.Foody.cart.entitiy.model.CartReq;
import com.yasu.Foody.cart.response.CartResponse;
import com.yasu.Foody.security.dto.Message;
import reactor.core.publisher.Mono;

public interface CartService {

Mono<CartResponse> purchase (CartRedis cartRedis);
Mono<?> cart (CartReq cartReq);
}
