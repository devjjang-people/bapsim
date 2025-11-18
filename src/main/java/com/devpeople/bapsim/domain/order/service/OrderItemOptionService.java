package com.devpeople.bapsim.domain.order.service;

import com.devpeople.bapsim.domain.order.entity.OrderItemOption;
import com.devpeople.bapsim.domain.order.repository.OrderItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사용자 전용 주문 옵션 서비스
 */

@Service
@RequiredArgsConstructor
public class OrderItemOptionService {

    private final OrderItemOptionRepository orderItemOptionRepository;

    /**
     * 옵션 저장 (주문 생성 시)
     */
    public OrderItemOption saveOrderItemOption(OrderItemOption orderItemOption) {
        return orderItemOptionRepository.save(orderItemOption);
    }

    /**
     * 특정 주문 항목에 연결된 옵션 목록 조회
     */
    public List<OrderItemOption> getMyOrderItemOptions(Long orderItemId) {
        return orderItemOptionRepository.findByOrderItemId(orderItemId);
    }

    /**
     * 옵션 삭제
     */
    public void deleteOrderItemOption(Long id) {
        if (!orderItemOptionRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 옵션이 존재하지 않습니다.");
        }
        orderItemOptionRepository.deleteById(id);
    }



}
