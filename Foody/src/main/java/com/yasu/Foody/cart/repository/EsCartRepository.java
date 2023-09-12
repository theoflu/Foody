package com.yasu.Foody.cart.repository;

import com.yasu.Foody.cart.entitiy.CartEntity;
import com.yasu.Foody.cart.entitiy.CartEs;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

import java.util.UUID;

public interface EsCartRepository extends ReactiveElasticsearchRepository<CartEs, UUID> {
}
