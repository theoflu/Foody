package com.yasu.Foody.cart.service.impl;


import com.yasu.Foody.account.entity.UserEntity;

import com.yasu.Foody.account.service.UserService;
import com.yasu.Foody.cart.entitiy.CartEntity;
import com.yasu.Foody.cart.entitiy.CartEs;
import com.yasu.Foody.cart.entitiy.model.CartReq;
import com.yasu.Foody.cart.repository.CartRepository;
import com.yasu.Foody.cart.response.CartResponse;
import com.yasu.Foody.cart.service.CartEsService;
import com.yasu.Foody.cart.service.CartService;

import com.yasu.Foody.product.domain.Product;

import com.yasu.Foody.product.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

import java.rmi.AlreadyBoundException;
import java.util.Date;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {


    private final UserService userService;
    private final ProductService productService;
    private final CartEsService cartEsService;
    private final CartRepository cartRepository;

    private Long cartID(){

        Random random=new Random();
        Long cart= (long) (random.nextDouble() * Long.MAX_VALUE);;
        return cart;
    }
    public Mono<CartResponse> save(CartReq cartReq) {
        Mono<UserEntity> userEntityMono = userService.findUserByEmail(cartReq.getUsername());

        Mono<Product> productMono = productService.findProductByProductCode(cartReq.getProductCode());

        return Mono.zip(userEntityMono, productMono)
                .flatMap(tuple -> {
                    UserEntity userEntity = tuple.getT1();
                    Product product = tuple.getT2();

                    if (product.getProductStock() <= 0) {
                        return Mono.error(new AlreadyBoundException("Ürün stokta bulunmuyor."));
                    }

                    CartEntity cartEntity = CartEntity.builder()
                            .id(cartID())
                            .userId(userEntity.getId())
                            .productId(product.getId())
                            .createdTime(new Date())
                            .createdBy(cartReq.getUsername())
                            .build();

                    // Ürün stokunu güncelle
                    product.setProductStock(product.getProductStock() - 1);

                    return cartRepository.save(cartEntity)
                            .flatMap(savedCart -> productService.updateProductStock(product)
                                    .thenReturn(savedCart))
                            .flatMap(savedCart -> mapToDto(cartEsService.saveNewCart(savedCart)));
                })
                .switchIfEmpty(Mono.error(new AlreadyBoundException("Öyle bir kullanıcı veya ürün yok!!")));
    }
    private Mono<CartResponse> mapToDto(Mono<CartEs> cartEs) {
        if (cartEs == null) {
            return Mono.empty(); // Boş bir Mono döndürün.
        }

        return  cartEs.map(cartEs1 -> CartResponse.builder()
                .id(cartEs1.getId())
                .userId(cartEs1.getUserId())
                .productId(cartEs1.getProductId())
                .build());


    }
}

