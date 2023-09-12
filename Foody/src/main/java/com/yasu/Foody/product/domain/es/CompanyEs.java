package com.yasu.Foody.product.domain.es;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class CompanyEs {
    private Long id;
    private String name;
}
