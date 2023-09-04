package com.yasu.Foody.cart.service;

import com.yasu.Foody.cart.entitiy.model.CartReq;
import com.yasu.Foody.cart.response.CartResponse;
import reactor.core.publisher.Mono;

public interface CartService {

Mono<CartResponse> save (CartReq cartReq);
}
