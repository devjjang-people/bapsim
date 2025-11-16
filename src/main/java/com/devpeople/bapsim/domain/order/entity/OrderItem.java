package com.devpeople.bapsim.domain.order.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 주문 항목(OrderItem) 엔티티
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

    /**
     * 주문 항목 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상위 주문 ID
     */
    private Long orderId;

    /**
     * 주문된 상품의 ID
     */
    private Long productId;

    /**
     * 주문 시점의 상품명
     */
    private String productName;

    /**
     * 주문 수량
     */
    private Integer quantity;

    /**
     * 당시 가격
     */
    private Integer unitPrice;

    /**
     * 소계 (quantity * unitPrice + 옵션 금액)
     */
    private Integer lineTotal;

    /**
     * 요청 사항
     */
    private String note;
}
