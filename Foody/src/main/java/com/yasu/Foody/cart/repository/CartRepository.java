package com.yasu.Foody.cart.repository;

import com.yasu.Foody.cart.entitiy.CartEntity;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;



public interface CartRepository extends ReactiveMongoRepository<CartEntity, Long> {
}
