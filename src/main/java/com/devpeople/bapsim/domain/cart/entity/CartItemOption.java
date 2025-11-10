package com.devpeople.bapsim.domain.cart.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 장바구니 아이템 옵션 테이블
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_item_option")
@Builder
public class CartItemOption extends BaseEntity {

    /** 기본키 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** cart item FK */
    @Column(nullable = false)
    private Long cartItemId;

    /** 실제 상품 옵션 FK */
    @Column(nullable = false)
    private Long productOptionId;

    /** 옵션 추가금 */
    @Column(nullable = false)
    private Integer extraPrice;

}
