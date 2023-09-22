package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.SellerUserEntity;

import com.yasu.Foody.security.dto.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface SellerUserRepository extends ReactiveMongoRepository<SellerUserEntity, Long> {
    Mono<SellerUserEntity> findBysellerName (String name);
}
