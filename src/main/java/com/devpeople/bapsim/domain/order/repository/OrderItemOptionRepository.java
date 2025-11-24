package com.devpeople.bapsim.domain.order.repository;

import com.devpeople.bapsim.domain.order.entity.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {

    /**
     * 주문 항목에 연결된 옵션 목록 조회
     */
    List<OrderItemOption> findByOrderItemId(Long orderItemId);
}
