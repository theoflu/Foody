package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.roles.ERole;
import com.yasu.Foody.account.entity.roles.Role;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends ReactiveMongoRepository <Role, Long> {
    Mono<Role> findByName(ERole name);
}
