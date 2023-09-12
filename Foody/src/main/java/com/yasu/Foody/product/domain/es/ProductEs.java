package com.yasu.Foody.product.domain.es;



import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.yasu.Foody.product.domain.moneyType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


@Data
@Document(indexName = "product")
@EqualsAndHashCode(of="id")
@Builder
public class ProductEs {

    @Id
    private Long id;
    private String name;
    private  String productCode;//name+model
    private  String description;
    private  CompanyEs seller;
    private  String features;
    private LinkedHashMap<moneyType ,BigDecimal> Price; // HASHSET veya herhangi set olmalı
    private  CategoryEs category;
    private List<String> images;
    private int productStock;

    private Boolean active;

}
