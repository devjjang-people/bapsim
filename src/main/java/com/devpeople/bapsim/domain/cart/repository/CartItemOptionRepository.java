package com.devpeople.bapsim.domain.cart.repository;

import com.devpeople.bapsim.domain.cart.entity.CartItemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemOptionRepository extends JpaRepository<CartItemOption, Long> {
}
