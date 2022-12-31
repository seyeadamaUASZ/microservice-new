package com.sid.gl.service;

import com.sid.gl.dto.ProductRequest;
import com.sid.gl.dto.ProductResponse;
import com.sid.gl.mapper.ProductMapper;
import com.sid.gl.model.Product;
import com.sid.gl.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .description(productRequest.getDescription())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build();
        this.productRepository.save(product);
        log.info("Product saved successfully !! on id {} ",product.getId());
    }

    public List<ProductResponse> allProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .filter(Objects::nonNull)
                .map(ProductMapper::convertToProductResponse)
                .collect(Collectors.toList());

    }
}
