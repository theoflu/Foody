package com.yasu.Foody.cart.repository;

import com.yasu.Foody.cart.entitiy.CartEntity;
import com.yasu.Foody.cart.entitiy.CartEs;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;



public interface EsCartRepository extends ReactiveElasticsearchRepository<CartEs, Long> {
}
