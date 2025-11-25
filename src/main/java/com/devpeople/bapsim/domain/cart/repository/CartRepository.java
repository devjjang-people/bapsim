package com.devpeople.bapsim.domain.cart.repository;

import com.devpeople.bapsim.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * 유저 + 가게 기준 장바구니 단건 조회
     *  - 한 유저는 한 가게에 대해 장바구니 1개만 생성할수있음
     */
    Optional<Cart> finByUserIdAndUserId(Long userId, Long storeId);
}
