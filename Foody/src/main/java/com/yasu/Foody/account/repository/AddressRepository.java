package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.AddressEntity;
import com.yasu.Foody.account.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AddressRepository extends ReactiveMongoRepository<AddressEntity, Long> {
    Mono<AddressEntity> findUserByAddress(String address);
}
