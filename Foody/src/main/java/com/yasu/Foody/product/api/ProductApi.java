package com.yasu.Foody.product.api;


import com.yasu.Foody.account.repository.SellerUserRepository;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.es.ProductEs;
import com.yasu.Foody.product.model.product.ProductDetailResponse;
import com.yasu.Foody.product.model.product.ProductResponse;
import com.yasu.Foody.product.service.product.ProductService;

import lombok.RequiredArgsConstructor;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")

public class ProductApi {

    private final ProductService productService;


    @GetMapping

    public Flux<ProductResponse> getAllProducts(){
        return productService.getAll();
    }
    @GetMapping("/company/{name}")
    public  Flux<ProductEs> getCompanyProducts(@PathVariable("name")String name){
        return productService.getCompanyProducts(name);
    }
    @GetMapping("/v1")
  //  @PreAuthorize("hasRole('ADMIN')")
    public Flux<Product> getAlProducts(){
        return productService.getAl();
    }

    @GetMapping("/{id}")
    public Mono<ProductResponse> getProductDetail(@PathVariable("id" ) Long id){
        return productService.getProductDetail(id);
    }




}
