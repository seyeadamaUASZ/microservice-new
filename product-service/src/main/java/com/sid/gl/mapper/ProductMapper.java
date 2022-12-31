package com.sid.gl.mapper;

import com.sid.gl.dto.ProductResponse;
import com.sid.gl.model.Product;

public class ProductMapper {

    public static ProductResponse convertToProductResponse(Product product){
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        return response;
    }
}
