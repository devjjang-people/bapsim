package com.devpeople.bapsim.domain.order.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 주문 항목 옵션(OrderItemOption) 엔티티
*/
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_item_option")
public class OrderItemOption extends BaseEntity {

    /**
     * 주문 옵션 고유 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상위 주문 항목 ID
     */
    private Long orderItemId;

    /**
     * 상품 옵션 ID
     */
    private Long productOptionId;

    /**
     * 옵션 명
     */
    private String optionName;

    /**
     * 추가 금액(옵션에 따른)
     */
    private Integer extraPrice;

}
