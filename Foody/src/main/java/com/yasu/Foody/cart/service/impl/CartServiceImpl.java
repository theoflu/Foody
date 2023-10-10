package com.yasu.Foody.cart.service.impl;


import com.yasu.Foody.account.entity.UserEntity;

import com.yasu.Foody.account.service.UserService;
import com.yasu.Foody.cart.entitiy.CartEntity;
import com.yasu.Foody.cart.entitiy.CartEs;
import com.yasu.Foody.cart.entitiy.CartRedis;
import com.yasu.Foody.cart.entitiy.model.CartReq;
import com.yasu.Foody.cart.repository.CartRepository;
import com.yasu.Foody.cart.repository.RedisCartRepository;
import com.yasu.Foody.cart.response.CartResponse;
import com.yasu.Foody.cart.service.CartEsService;
import com.yasu.Foody.cart.service.CartService;

import com.yasu.Foody.product.domain.Product;

import com.yasu.Foody.product.service.product.ProductService;
import com.yasu.Foody.security.dto.Message;
import io.swagger.v3.core.util.AnnotationsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.rmi.AlreadyBoundException;
import java.util.*;


@Service
@RequiredArgsConstructor

public class CartServiceImpl implements CartService {


    private final UserService userService;
    private final ProductService productService;
    private final CartEsService cartEsService;
    private final CartRepository cartRepository;
    private final RedisCartRepository redisCartRepository;

    private Long cartID(){

        Random random=new Random();
        Long cart= (long) (random.nextDouble() * Long.MAX_VALUE);;
        return cart;
    }
    @Override
    public Mono<CartResponse> purchase(CartRedis cartReq) {
        List<String> productCodeList = new ArrayList<>(cartReq.getProductCC().keySet());
        List<Integer> productCountList = new ArrayList<>(cartReq.getProductCC().values());
        HashMap<String, Integer> sepet = new HashMap<>();

        Flux<String> productCodeFlux = Flux.fromIterable(productCodeList);
        Flux<Integer> productCountFlux = Flux.fromIterable(productCountList);
        Flux<String> productCodeWithCheck = productCodeFlux.flatMap(code -> prodCheck(code));

        return userService.findUserByEmail(cartReq.getUsername()).flatMap(userEntity -> {
            return Flux.zip(productCodeWithCheck, productCountFlux, (code, count) -> {
                sepet.put(code, count);
                return code;
            }).collectList().flatMap(codes -> {
                CartEntity cartEntity = CartEntity.builder()
                        .id(cartID())
                        .userId(userEntity.getId())
                        .productId(sepet)
                        .createdTime(new Date())
                        .createdBy(cartReq.getUsername())
                        .build();
                return Flux.fromIterable(productCodeList)
                        .flatMap(productCode -> setUpdateProduct(productCode, sepet.get(productCode)))
                        .collectList()
                        .flatMap(updatedProducts -> {
                            boolean hasInsufficientStock = updatedProducts.stream().anyMatch(result -> result.equals("Yetersiz stok! "));
                            if (hasInsufficientStock) {
                                return Mono.error(new IllegalStateException("Yetersiz stok! "));
                            }
                            return cartRepository.save(cartEntity).thenReturn(cartEntity);
                        });
            }).flatMap(savedCart -> mapToDto(cartEsService.saveNewCart(savedCart)));
        }).switchIfEmpty(Mono.error(new AlreadyBoundException("Öyle bir kullanıcı yok!!")));
    }

    private Mono<String> prodCheck(String productCode) {
        return productService.findProductByProductCode(productCode).map(Product::getProductCode);
    }

    private Mono<String> setUpdateProduct(String productCode, Integer productCount) {
        return productService.findProductByProductCode(productCode).flatMap(products -> {
            int newStock = products.getProductStock() - productCount;
            if (newStock < 0) {
                return Mono.just("Yetersiz stok: ");
            }
            products.setProductStock(newStock);
            return productService.updateProductStock(products).thenReturn("İşlem tamam");
        });
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


    @Override
    public Mono<?> cart(CartReq cartReq) {
        CartRedis cartRedis=CartRedis.builder()
                .id(cartID())
                .productCC(cartReq.getProductCC())
                .username(cartReq.getUsername())
                .build();
        CartRedis a = redisCartRepository.findByUsername(cartReq.getUsername());
        if(a!=null){
            a.setProductCC(cartReq.getProductCC());
            return this.maptoDto(redisCartRepository.save(a));

        }
        return this.maptoDto(redisCartRepository.save(cartRedis));

    }

    private Mono<?> maptoDto(CartRedis cartRedis){
        return  Mono.just(cartRedis.getId());
    }
}

