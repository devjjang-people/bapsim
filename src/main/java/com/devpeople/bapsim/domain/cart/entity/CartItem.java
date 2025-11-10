package com.devpeople.bapsim.domain.cart.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 장바구니 아이템 테이블
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_item")
@Builder
public class CartItem extends BaseEntity {

    /** 기본키 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** cart FK */
    @Column(nullable = false)
    private Long cartId;

    /** product FK */
    @Column(nullable = false)
    private Long productId;

    /** 담은 수량 */
    @Column(nullable = false)
    private Integer quantity;

    /** 담을 당시 상품 금액  */
    @Column(nullable = false)
    private Integer unitPrice;

    /** 요청사항 */
    private String note;


}
