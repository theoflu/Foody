package com.yasu.Foody.product.repository.es;

import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.es.ProductEs;


import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductEsRepository extends ReactiveElasticsearchRepository<ProductEs,UUID> {

    Flux<ProductEs> findBySellerId(UUID companyId);
    Mono<ProductEs> findById(UUID uuid);



}
