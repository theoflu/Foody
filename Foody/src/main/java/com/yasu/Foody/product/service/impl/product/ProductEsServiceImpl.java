package com.yasu.Foody.product.service.impl.product;

import com.yasu.Foody.account.entity.SellerUserEntity;
import com.yasu.Foody.account.repository.SellerUserRepository;
import com.yasu.Foody.account.service.UserService;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.ProductImage;
import com.yasu.Foody.product.domain.category.Category;
import com.yasu.Foody.product.domain.es.CategoryEs;
import com.yasu.Foody.product.domain.es.CompanyEs;
import com.yasu.Foody.product.domain.es.ProductEs;
import com.yasu.Foody.product.domain.moneyType;
import com.yasu.Foody.product.model.ProductSellerResponse;
import com.yasu.Foody.product.model.category.CategoryResponse;
import com.yasu.Foody.product.repository.ProductRepository;
import com.yasu.Foody.product.repository.es.ProductEsRepository;
import com.yasu.Foody.product.service.category.CategoryService;
import com.yasu.Foody.product.service.impl.category.CategoryServiceImpl;
import com.yasu.Foody.product.service.product.ProductEsService;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.rmi.AlreadyBoundException;
import java.util.LinkedHashMap;

import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor

public class ProductEsServiceImpl implements ProductEsService{


    private final ProductEsRepository productEsRepository ;

    private final ProductRepository productRepository ;

    private final CategoryService categoryService;
    private final UserService userService;
    private final SellerUserRepository sellerUserRepository;

    @Override
    public Flux<ProductEs> getCompanyProducts(String name) {
        return sellerUserRepository.findBysellerName(name)
                .flatMap(sellerUserEntity -> {
                    return Mono.just(productEsRepository.findBySellerId(sellerUserEntity.getId()));
                })
                .switchIfEmpty(Mono.error(new AlreadyBoundException("Kullanıcı bulunamadı")))
                .flatMapMany(Flux::from);
    }


    @Override
    public Mono<ProductEs> saveNewProduct(Product product) {
        return getProductCategory(product.getCategoryId())
                .flatMap(category -> findSellername(product.getCompanyID())
                        .flatMap(seller -> {
                            return productEsRepository.save(ProductEs.builder()
                                    .active(product.getActive())
                                    .productCode(product.getProductCode())
                                    .description(product.getDescription())
                                    .features(product.getFeatures())
                                    .id(product.getId())
                                    .name(product.getName())
                                    .Price(product.getPrice())
                                    .productStock(product.getProductStock())
                                    .seller(seller)  // Burada `seller` nesnesini doğrudan atıyoruz.
                                    .category(category)
                                    .images(product.getProductImage().stream().map(ProductImage::getUrl).collect(Collectors.toList()))
                                    .build());
                        }));
    }

    private Mono<CompanyEs> findSellername(Long id) {

         return userService.findUserById(id)
                 .map(seller -> CompanyEs.builder()
                         .id(seller.getId())
                         .name(seller.getSellerName())
                         .build());
    }

    private Mono<CategoryEs> getProductCategory(String categoryId) {
        return categoryService.getById(categoryId)
                .map(category -> CategoryEs.builder()
                        .name(category.getName())
                        .id(category.getId())
                        .build());
    }
/*

 public Mono<ProductEs> saveNewProduct(Product product) {

      return  productEsRepository.save( ProductEs.builder()
                .active(product.getActive())
                .productCode(product.getProductCode())
                .description(product.getDescription())
                .features(product.getFeatures())
                .id(product.getId())
                .name(product.getName())
                .Price(product.getPrice())
              .productStock(product.getProductStock())
              //TODO get company name and code
                .seller(CompanyEs.builder().id(product.getId()).name("Test").build())
               .category(getProductCategory(product.getCategoryId()))
                //      .images(product.getProductImage().stream()
                     //         .map(ProductImage::getUrl)
                         //     .collect(Collectors.toList()))
                .build());


    }
    private Mono<CategoryEs> getProductCategory(String categoryId) {
        return categoryService.getById(categoryId)
                .map(category -> CategoryEs.builder()
                        .name(category.getName())
                        .id(category.getId())
                        .build());
    }
 */
    @Override
    public Flux<ProductEs> findAl() {


        return productEsRepository.findAll();
    }
    @Override
    public Flux<Product> findAll() {


        return productRepository.findAll();
    }

    @Override
    public Mono<ProductEs> finById(Long id) {
        return productEsRepository.findById(id);
    }
}
