package com.yasu.Foody.product.repository;

import com.yasu.Foody.product.domain.Product;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




public interface ProductRepository extends ReactiveMongoRepository<Product,Long> {
   Mono<Product> findProductBy(String productCode);

   Mono<Product> findProductByProductCode(String productCode);
   Flux<Product> findBycompanyID(Long companyid);


   Mono<Product> findProductById(Long id);
}
