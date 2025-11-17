package com.devpeople.bapsim.domain.product.repository;

import com.devpeople.bapsim.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

