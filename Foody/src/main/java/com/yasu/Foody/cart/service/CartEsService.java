package com.yasu.Foody.cart.service;

import com.yasu.Foody.cart.entitiy.CartEntity;
import com.yasu.Foody.cart.entitiy.CartEs;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.es.ProductEs;
import reactor.core.publisher.Mono;

public interface CartEsService {
    Mono<CartEs> saveNewCart(CartEntity cartEntity);

}
