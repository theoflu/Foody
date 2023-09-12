package com.yasu.Foody.product.startup;

import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.service.UserService;
import com.yasu.Foody.filestore.service.FileStoreService;
import com.yasu.Foody.product.domain.category.Category;
import com.yasu.Foody.product.domain.moneyType;
import com.yasu.Foody.product.model.category.CategoryResponse;
import com.yasu.Foody.product.model.category.CategorySaveRequest;
import com.yasu.Foody.product.model.product.ProductResponse;
import com.yasu.Foody.product.model.product.ProductSaveRequest;
import com.yasu.Foody.product.repository.ProductRepository;
import com.yasu.Foody.product.repository.es.ProductEsRepository;
import com.yasu.Foody.product.service.category.CategoryService;
import com.yasu.Foody.product.service.product.ProductService;
import io.netty.util.internal.ResourcesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.UUID.randomUUID;

@Slf4j
@Component
@RequiredArgsConstructor

public class ProductDemoData {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductEsRepository productEsRepository;
    private final ProductRepository productRepository;
    private final FileStoreService fileStoreService;
    private final UserService userService;
 /*
    @EventListener(ApplicationReadyEvent.class)
    public void migrate() {

      // productRepository.deleteAll().block();

        UUID countOfData=productService.count().block();

        assert countOfData != null;
        if(countOfData.equals(0L)){
            productEsRepository.deleteAll().block();

            CategoryResponse categoryResponse = categoryService.save(
                    CategorySaveRequest.builder().id("Ç001").name("corba").build());



            IntStream.range(0,20).forEach(i-> {
                        LinkedHashMap<moneyType,BigDecimal> prc= new LinkedHashMap<>(){
                    {
                        put(moneyType.USD, BigDecimal.valueOf((i+1)*5));
                    }};
                String uuid=UUID.randomUUID().toString();
                        byte[] file=null;
                        try {

                             file = Files.readAllBytes(ResourceUtils.getFile("classpath:product-images/foto.jpg").toPath());

                        }catch (IOException e) { log.error("File Read Error");}
                        fileStoreService.saveImage(uuid,new ByteArrayInputStream(file));

            ProductResponse response= productService.save(
                        ProductSaveRequest.builder()
                                .sellerId("a"+randomUUID().toString())
                                .id(randomUUID().toString())
                                .productCode(i+randomUUID().toString())
                                    .available(3)
                                .description("Product Descip"+i)
                                .categoryId(categoryResponse.getId())
                                .name("Product Name "+i)
                                .features("<li>Black Color </li>"+i)
                                .Price(prc)
                                .productStock(14+i)
                                .images(List.of(uuid))
                                .build());








            }


            );

        }




    }
*/
}
