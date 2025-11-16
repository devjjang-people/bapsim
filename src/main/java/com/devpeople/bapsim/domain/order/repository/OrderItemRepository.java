package com.devpeople.bapsim.domain.order.repository;

import com.devpeople.bapsim.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * 주문 ID에 속한 모든 상품 항목 조회
     * (주문 상세 화면)
     */
    List<OrderItem> findByOrderId(Long orderId);
}
