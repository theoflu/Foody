package com.yasu.Foody.product.service.impl.product;

import com.yasu.Foody.filestore.service.FileStoreService;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.ProductImage;
import com.yasu.Foody.product.domain.es.ProductEs;
import com.yasu.Foody.product.domain.moneyType;
import com.yasu.Foody.product.model.product.ProductDetailResponse;
import com.yasu.Foody.product.model.product.ProductResponse;
import com.yasu.Foody.product.model.product.ProductSaveRequest;
import com.yasu.Foody.product.model.ProductSellerResponse;
import com.yasu.Foody.product.repository.ProductRepository;
import com.yasu.Foody.product.service.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.util.ResourceUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor// bu construterı otomatik yapıyo diğeri açık şekilde yapıyo

public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ProductDeliveryService productDeliveryServiceImpl;
    private final ProductAmountService productAmountService;
    private final ProductEsService productEsService;
    private final FileStoreService fileStoreService;

    @Override
    public Flux<ProductResponse> getAll() {

        return productEsService.findAl().map(this::mapToDto);

    }

    @Override
    public Flux<Product> getAl() {

        return productEsService.findAll();

    }

    @Override
    public Mono<Product> findProductBy(String productCode) {
        return productRepository.findProductBy(productCode);
    }

    @Override
    public Mono<Product> findProductByProductCode(String productCode) {
        return productRepository.findProductByProductCode(productCode);
    }

    @Override //TODO bu yapı düzelcek product artık elle girilebilecek önce blockları kaldırıp
    //TODO .. basit bir ürün ekle sonra şu olay gerektiren resim falan olayını yap.
    public Mono<ProductResponse> save(ProductSaveRequest productSaveRequest) {

        Product product = Product.builder()
                .id(productSaveRequest.getId())
                .active(Boolean.TRUE)
                .productCode(productSaveRequest.getProductCode())
                .categoryId(productSaveRequest.getCategoryId())
                .companyID(productSaveRequest.getSellerId())
                .description(productSaveRequest.getDescription())
                .features(productSaveRequest.getFeatures())
                .name(productSaveRequest.getName())
                .Price(productSaveRequest.getPrice())
                .productStock(productSaveRequest.getProductStock())
                .productImage(productSaveRequest.getImages().stream().map(it -> new ProductImage(ProductImage.ImageType.FEATURE, it)).collect(Collectors.toList()))//Resim listesindeki tüm elemanları getirip içinde dolaşmak içins
                .build();
        savePic(productSaveRequest.getId()+productSaveRequest.getProductCode(),productSaveRequest.getImages());

        return productRepository.save(product)
                .flatMap(savedProduct -> {
                    // savedProduct ile ilgili işlemleri gerçekleştirin
                    return mapToDto(productEsService.saveNewProduct(savedProduct));
                });


    }

    private void savePic(String idpcode, List<String> images)  {
        try {
            String uuid = idpcode;
            byte[] file = Files.readAllBytes(ResourceUtils.getFile("classpath:" + images.get(0)).toPath());
            fileStoreService.saveImage(uuid, new ByteArrayInputStream(file));
        } catch (IOException e) {
            new AlreadyBoundException(e.getMessage());
        }

}

    @Override
    public Mono<Long> count() {
        return productRepository.count();
    }

    @Override
    public Mono<ProductDetailResponse> getProductDetail(String id) {
        return null;// this.mapToDto(productEsService.finById(id));

    }

    @Override
    public Mono<Product> updateProductStock(Product product) {
Product prd=Product.builder()  .id(product.getId())
        .active(Boolean.TRUE)
        .productCode(product.getProductCode())
        .categoryId(product.getCategoryId())
        .description(product.getDescription())
        .features(product.getFeatures())
        .name(product.getName())
        .Price(product.getPrice())
        .productStock(product.getProductStock())
        .build();


        return productRepository.save(prd);
    }
    private ProductResponse mapToDto(ProductEs productEs) {
        if(productEs==null) {
            return null;
        } //Resim gözükmüyor ona bi bakılacak
        ProductResponse productResponse =  ProductResponse.builder()
                //TODO client request üzridn validate edilecek

                .moneySymbol(moneyType.USD.getSymbol())
                .productCode(productEs.getProductCode())
                .name(productEs.getName())
                .id(productEs.getId())
                .description(productEs.getDescription())
                .deliveryIn(productDeliveryServiceImpl.getDeliveryInfo(productEs.getId()))
//                .categoryId(productEs.getCategory().getId())
                .available(productAmountService.getByProductId(productEs.getId()))
                .freeDelivery(true)
                .deliveryIn("3")
                .features(productEs.getFeatures())
                .productStock(productEs.getProductStock())
                .image(productEs.getImages().get(0))
                .seller(ProductSellerResponse.builder().id(productEs.getSeller().getId()).name(productEs.getSeller().getName()).build())
                .build();

        return productResponse;
    }
    private Mono<ProductResponse> mapToDto(Mono<ProductEs> productEsMono) {
        if(productEsMono==null) {
            return null;
        }
        return   productEsMono.map(productEs -> ProductResponse.builder()
                //TODO client request üzridn validate edilecek
                .price(productEs.getPrice().get(moneyType.USD))
                .moneySymbol(moneyType.USD.getSymbol())
                .productCode(productEs.getProductCode())
                .name(productEs.getName())
                .id(productEs.getId())
                .description(productEs.getDescription())
                .deliveryIn(productDeliveryServiceImpl.getDeliveryInfo(productEs.getId()))
                .categoryId(productEs.getCategory().getId())
                .available(productAmountService.getByProductId(productEs.getId()))
                .freeDelivery(true)
                .deliveryIn("3")
                .features(productEs.getFeatures())
                .productStock(productEs.getProductStock())
                .image(productEs.getImages().get(0))
                .seller(ProductSellerResponse.builder().id(productEs.getSeller().getId()).name(productEs.getSeller().getName()).build())
                .build());
    }

}
