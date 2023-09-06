package com.yasu.Foody.cart.service.impl;

import com.yasu.Foody.cart.entitiy.CartEntity;
import com.yasu.Foody.cart.entitiy.CartEs;
import com.yasu.Foody.cart.repository.EsCartRepository;
import com.yasu.Foody.cart.service.CartEsService;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.ProductImage;
import com.yasu.Foody.product.domain.es.CompanyEs;
import com.yasu.Foody.product.domain.es.ProductEs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartEsServiceImpl implements CartEsService {
    private final EsCartRepository esCartRepository;
    @Override
    public Mono<CartEs> saveNewCart(CartEntity cartEntity) {
        return  esCartRepository.save( CartEs.builder()
                .id(cartEntity.getId())
                .userId(cartEntity.getUserId())
                .productId(cartEntity.getProductId())
                .createdTime(new Date())
                .createdBy("")
                .build());
    }
}
