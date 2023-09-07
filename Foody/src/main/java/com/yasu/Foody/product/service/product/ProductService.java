package com.yasu.Foody.product.service.product;


import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.model.product.ProductDetailResponse;
import com.yasu.Foody.product.model.product.ProductResponse;
import com.yasu.Foody.product.model.product.ProductSaveRequest;
import com.yasu.Foody.product.model.product.UpdateProductActive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
     Mono<Product> deleteProduct(UpdateProductActive id);
    Flux<ProductResponse> getAll();
    Flux<Product> getAl();

    Mono<Product> findProductByProductCode(String productCode);
   Mono<ProductResponse> save(ProductSaveRequest productSaveRequest);

   Mono<Long> count();

   Mono<ProductDetailResponse> getProductDetail(String id);

 Mono<Product> updateProductStock(Product product);


}
