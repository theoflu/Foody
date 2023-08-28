package com.yasu.Foody.product.repository;
import com.yasu.Foody.product.domain.category.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category,String> {
}
