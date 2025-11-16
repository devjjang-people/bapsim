package com.devpeople.bapsim.domain.order.repository;

import com.devpeople.bapsim.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * 특정 사용자(userId)의 주문 목록 조회
     */
    List<Order> findByUserId(Long userId);



}
