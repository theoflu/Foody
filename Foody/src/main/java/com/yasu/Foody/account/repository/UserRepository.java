package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.security.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserEntity,String> {
 //Optional<UserEntity> findByEmailAndPassword(String email, String password);
 Mono<UserEntity> findUserByEmail(String userMail);

 Mono<UserDetails> findByEmail(String username);

 Mono<Boolean> existsByEmail(String email);

}
