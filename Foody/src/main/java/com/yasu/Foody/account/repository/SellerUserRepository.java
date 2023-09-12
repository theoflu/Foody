package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.SellerUserEntity;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SellerUserRepository extends ReactiveMongoRepository<SellerUserEntity, Long> {
    Mono<SellerUserEntity> findBysellerName (String name);
    Mono<SellerUserEntity> findSellerByUserId (Long userId);

}
