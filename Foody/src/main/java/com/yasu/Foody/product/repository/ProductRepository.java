package com.yasu.Foody.product.repository;

import com.yasu.Foody.product.domain.Product;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
