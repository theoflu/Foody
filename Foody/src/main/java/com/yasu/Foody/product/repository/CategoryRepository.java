package com.yasu.Foody.product.repository;
import com.yasu.Foody.product.domain.category.Category;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;



public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> getById(String id);
}
