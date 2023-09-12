package com.yasu.Foody.product.domain.es;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CompanyEs {
    private UUID id;
    private String name;
}
