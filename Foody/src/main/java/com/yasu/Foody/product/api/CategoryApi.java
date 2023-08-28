package com.yasu.Foody.product.api;

import com.yasu.Foody.product.domain.category.Category;
import com.yasu.Foody.product.model.category.CategoryResponse;
import com.yasu.Foody.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryApi {

    private final CategoryService categoryService;
    @GetMapping
    public Flux<Category> getAll(){
        return categoryService.getAll();
    }
    @GetMapping("/{id}")
    public Mono<Category> getById(@PathVariable("id") String id){
        return categoryService.getById(id);
    }
}
