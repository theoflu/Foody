package com.yasu.Foody.cart.repository;

import com.yasu.Foody.cart.entitiy.CartEntity;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface CartRepository extends ReactiveMongoRepository<CartEntity, UUID> {
}
