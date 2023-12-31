package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.UserEntity;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, Long> {
 //Optional<UserEntity> findByEmailAndPassword(String email, String password);
 Mono<UserEntity> findUserByEmail(String userName);
// Mono<UserEntity> findUserBy(String username);

 Mono<UserDetails> findByEmail(String username);
// Mono<UserEntity> findByA(String userName);

Mono<Boolean> existsById(Long id);

}
