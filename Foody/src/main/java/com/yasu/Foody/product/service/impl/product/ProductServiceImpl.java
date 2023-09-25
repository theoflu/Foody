package com.yasu.Foody.product.service.impl.product;

import com.yasu.Foody.account.entity.SellerUserEntity;
import com.yasu.Foody.account.repository.SellerUserRepository;
import com.yasu.Foody.filestore.service.FileStoreService;
import com.yasu.Foody.product.domain.Product;
import com.yasu.Foody.product.domain.ProductImage;
import com.yasu.Foody.product.domain.es.ProductEs;
import com.yasu.Foody.product.domain.moneyType;
import com.yasu.Foody.product.model.product.ProductDetailResponse;
import com.yasu.Foody.product.model.product.ProductResponse;
import com.yasu.Foody.product.model.product.ProductSaveRequest;
import com.yasu.Foody.product.model.ProductSellerResponse;
import com.yasu.Foody.product.model.product.UpdateProductActive;
import com.yasu.Foody.product.repository.ProductRepository;
import com.yasu.Foody.product.repository.es.ProductEsRepository;
import com.yasu.Foody.product.service.product.*;
import lombok.RequiredArgsConstructor;


import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import org.springframework.util.ResourceUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.AlreadyBoundException;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor// bu construterı otomatik yapıyo diğeri açık şekilde yapıyo

public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ProductEsRepository productEsRepository;
    private final ProductDeliveryService productDeliveryServiceImpl;
    private final ProductAmountService productAmountService;
    private final ProductEsService productEsService;
    private final FileStoreService fileStoreService;
    private final SellerUserRepository sellerUserRepository;
    private AtomicLong productIdCounter = new AtomicLong(0);
    private Long productID(){

      return  productIdCounter.incrementAndGet();
    }

    @Override
    public Flux<ProductResponse> getAll() {

        return productEsService.findAl()
                .filter(sd->sd.getActive())
                .map(this::mapToDto);

    }

    @Override
    public Flux<ProductEs> getCompanyProducts(String name) {
        return productEsService.getCompanyProducts(name).filter(prd->prd.getActive().equals(true));
    }


    @Override
    public Flux<Product> getAl() {

        return productEsService.findAll();

    }
    @Override
    public Mono<Product> deleteProduct(UpdateProductActive id) {
        return productEsRepository.findById(id.getId())
                .flatMap(product -> {
                    product.setActive(id.isActive()); // Ürünün aktif durumunu güncelle
                    return productEsRepository.save(product)// Elasticsearch için  güncelleme yap ve kaydet
                            .then(productRepository.findProductById(id.getId())
                                    .flatMap(prd -> {
                                        prd.setActive(id.isActive());
                                        return productRepository.save(prd); // repos için de güncelleme yap ve kaydet
                                    }));
                })
                .switchIfEmpty(Mono.error(new AlreadyBoundException("BÖYLE BIR ÜRÜN YOK")));
    }




    @Override//
    public Mono<Product> findProductByProductCode(String productCode) {
        return productRepository.findProductByProductCode(productCode);
    }

    @Override //TODO bu yapı düzelcek product artık elle girilebilecek önce blockları kaldırıp
    //TODO .. basit bir ürün ekle sonra şu olay gerektiren resim falan olayını yap.
    public Mono<ProductResponse> save(ProductSaveRequest productSaveRequest) {

        Product product = Product.builder()
                .id(productID())
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
        savePic(productID()+productSaveRequest.getProductCode(),productSaveRequest.getImages());
       Mono<SellerUserEntity> s=sellerUserRepository.findById(product.getCompanyID());
        return s.flatMap(sd->{

         return    productRepository.save(product)
                    .flatMap(savedProduct -> {
                        // savedProduct ile ilgili işlemleri gerçekleştirin
                        return mapToDto(productEsService.saveNewProduct(savedProduct));
                    });

        }).switchIfEmpty(Mono.error(new  AlreadyBoundException("YOU ARE NOT SELLER!!")));


    }

    private void savePic(String idpcode, List<String> images)  {
            try {
                String Long = idpcode;
                for(int i =0;i<images.size();i++){
                    byte[] file = Files.readAllBytes(ResourceUtils.getFile("classpath:" + images.get(i)).toPath());
                    fileStoreService.saveImage(Long, new ByteArrayInputStream(file));
                }

            } catch (IOException e) {
                new AlreadyBoundException(e.getMessage());
            }

}

    @Override
    public Mono<Long> count() {
        return productRepository.count();
    }

    @Override
    public Mono<ProductResponse> getProductDetail(Long id) {
        return this.mapToDto(productEsService.finById(id));

    }

    @Override
    public Mono<Product> updateProductStock(Product product) {
            return productEsRepository.findById(product.getId())
                    .flatMap(prdEs -> {
                prdEs.setProductStock(product.getProductStock());
                return productEsRepository.save(prdEs).then(productRepository.findById(product.getId()).flatMap(prd->{
                    prd.setProductStock(product.getProductStock());
                    return productRepository.save(prd);
                }));
            });
    }
    private ProductResponse mapToDto(ProductEs productEs) {
        if(productEs==null) {
            return null;
        }
        ProductResponse productResponse =  ProductResponse.builder()
                //TODO client request üzridn validate edilecek

                .moneySymbol(moneyType.USD.getSymbol())
                .productCode(productEs.getProductCode())
                .name(productEs.getName())
                .id(productID())
                .description(productEs.getDescription())
                .deliveryIn(productDeliveryServiceImpl.getDeliveryInfo(productEs.getId()))
                .categoryId(productEs.getCategory().getId())
                .available(productAmountService.getByProductId(productEs.getId()))
                .freeDelivery(true)
                .deliveryIn("3")
                .features(productEs.getFeatures())
                .productStock(productEs.getProductStock())
                .image(productEs.getImages().get(0))
                .seller(findSellername(productEs.getSeller().getId()).block())
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
                .id(productID())
                .description(productEs.getDescription())
                .deliveryIn(productDeliveryServiceImpl.getDeliveryInfo(productEs.getId()))
                .categoryId(productEs.getCategory().getId())
                .available(productAmountService.getByProductId(productEs.getId()))
                .freeDelivery(true)
                .deliveryIn("3")
                .features(productEs.getFeatures())
                .productStock(productEs.getProductStock())
                .image(productEs.getImages().get(0))
                .seller(findSellername(productEs.getSeller().getId()).block())
                .build());
    }

    private Mono<ProductSellerResponse> findSellername(Long id) {
        return sellerUserRepository.findById(id)
                .map(seller -> ProductSellerResponse.builder()
                        .id(seller.getId())
                        .name(seller.getSellerName())
                        .build());
    }
}
