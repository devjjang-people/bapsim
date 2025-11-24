package com.devpeople.bapsim.domain.order.service;

import com.devpeople.bapsim.domain.order.entity.OrderItem;
import com.devpeople.bapsim.domain.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 사용자 전용 주문 항목 서비스
 */

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    /**
     * 주문 항목 저장
     */
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    /**
     * 특정 주문에 포함된 상품 목록 조회
     */
    public List<OrderItem> getMyOrderItems(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    /**
     * 주문 항목 삭제
     */
    public void deleteOrderItem(Long id) {
        if(!orderItemRepository.existsById(id)) {
            throw  new IllegalArgumentException("삭제할 주문 항목이 존재하지 않습니다.");
        }

        orderItemRepository.deleteById(id);
    }

}
