package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.SellerUserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SellerUserRepository extends ReactiveMongoRepository<SellerUserEntity,String> {
    Mono<SellerUserEntity> findBysellerName (String name);
}
