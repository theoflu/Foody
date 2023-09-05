package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.SellerUserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SellerUserRepository extends ReactiveMongoRepository<SellerUserEntity,String> {
}
