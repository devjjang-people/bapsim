package com.devpeople.bapsim.domain.cart.repository;

import com.devpeople.bapsim.domain.cart.entity.CartItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemOptionRepository extends JpaRepository<CartItemOption, Long> {
}
