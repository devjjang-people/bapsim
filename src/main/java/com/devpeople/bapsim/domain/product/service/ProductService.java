package com.devpeople.bapsim.domain.product.service;

import com.devpeople.bapsim.domain.product.entity.Product;
import com.devpeople.bapsim.domain.product.repository.ProductRepository;
import com.devpeople.bapsim.global.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductById(Integer id) {

        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product deleteProduct(Integer id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.setDescription("상품 삭제");
        product.setIsDeleted(true);

        return productRepository.save(product);
    }
}
