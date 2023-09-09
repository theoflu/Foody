package com.yasu.Foody.product.service.product;

import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.es.ProductEs;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductEsService {
    Mono<ProductEs> saveNewProduct(Product product);
    Flux<ProductEs> getCompanyProducts(String name);
     Flux<ProductEs> findAl();
     Flux<Product> findAll();
     Mono<ProductEs> finById(String id);

}
