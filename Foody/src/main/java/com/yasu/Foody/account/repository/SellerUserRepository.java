package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.SellerUserEntity;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SellerUserRepository extends ReactiveMongoRepository<SellerUserEntity, UUID> {
    Mono<SellerUserEntity> findBysellerName (String name);
    Mono<SellerUserEntity> findSellerByUserId (UUID userId);

}
