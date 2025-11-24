package com.devpeople.bapsim.domain.order.service;

import com.devpeople.bapsim.domain.order.entity.Order;
import com.devpeople.bapsim.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사용자 전용 주문 서비스
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     * 주문 생성
     */
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * 내 주문 단건 조회
     */
    public Order getMyOrder(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));

        if(!order.getUserId().equals(userId)) {
            throw new IllegalStateException("본인의 주문만 조회할 수 있습니다.");
        }

        return  order;
    }

    /**
     * 내 주문 전체 조회
     */
    public List<Order> getMyOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    /**
     * 내 주문 삭제
     */
    public void deleteMyOrder(Long userId, Long orderId) {
        Order order = getMyOrder(userId, orderId);
        orderRepository.delete(order);
    }

}
