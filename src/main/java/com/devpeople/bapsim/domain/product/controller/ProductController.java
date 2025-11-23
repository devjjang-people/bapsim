package com.devpeople.bapsim.domain.product.controller;

import com.devpeople.bapsim.domain.product.entity.Product;
import com.devpeople.bapsim.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // TODO 조회조건 추가 (ex. 사용자 식별자)
    @PostMapping("/productList")
    public List<Product> getProductList(){
        return productService.getProductList();
    }
}
