package com.yasu.Foody.product.service.impl.category;

import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.ProductImage;
import com.yasu.Foody.product.domain.category.Category;
import com.yasu.Foody.product.domain.moneyType;
import com.yasu.Foody.product.model.ProductSellerResponse;
import com.yasu.Foody.product.model.category.CategoryResponse;
import com.yasu.Foody.product.model.category.CategorySaveRequest;
import com.yasu.Foody.product.model.product.ProductResponse;
import com.yasu.Foody.product.repository.CategoryRepository;
import com.yasu.Foody.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public Flux<Category> getAll() {
        return categoryRepository.findAll() ;
    }

    @Override
    public Mono<Long> count() {
        return categoryRepository.count();
    }

    @Override
    public  CategoryResponse save(CategorySaveRequest categorySaveRequest) {
        Category cate = Category.builder()
                .name(categorySaveRequest.getName())
                .id(categorySaveRequest.getId()).build();

        return this.maptoDto(categoryRepository.save(cate).block());
    }


    @Override
    public Mono<Category> getById(String id) {
        Mono<Category> categoryMono=categoryRepository.findById(id);
        Mono<Category> categoryMono2=Mono.empty();
        if(categoryMono != categoryMono2){
            return categoryMono;
        }
        return null;
    }

    private CategoryResponse maptoDto(Category category){
        if(category==null) {
            return null;
        }
        return CategoryResponse.builder()
                .name(category.getName())
                .id(category.getId()).build();


    }

    }

