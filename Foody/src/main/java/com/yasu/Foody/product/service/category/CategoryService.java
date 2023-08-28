package com.yasu.Foody.product.service.category;

import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.category.Category;
import com.yasu.Foody.product.model.category.CategoryResponse;
import com.yasu.Foody.product.model.category.CategorySaveRequest;
import com.yasu.Foody.product.model.product.ProductResponse;
import com.yasu.Foody.product.model.product.ProductSaveRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Flux<Category> getAll();
    Mono<Category> getById(String id);
    Mono<Long> count();
    CategoryResponse save(CategorySaveRequest categorySaveRequest);
}
