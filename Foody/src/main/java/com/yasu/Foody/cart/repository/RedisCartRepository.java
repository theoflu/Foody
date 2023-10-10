package com.yasu.Foody.cart.repository;

import com.yasu.Foody.cart.entitiy.CartRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RedisCartRepository extends CrudRepository<CartRedis, String> {

     CartRedis findByUsername(@Param("username") String username);

}
