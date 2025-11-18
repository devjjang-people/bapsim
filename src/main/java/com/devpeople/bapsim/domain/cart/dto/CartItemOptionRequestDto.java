package com.devpeople.bapsim.domain.cart.dto;

import lombok.*;

/**
 * 장바구니 아이템 옵션 생성/수정 요청 DTO
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemOptionRequestDto {

    /** 장바구니 아이템 ID (cart_item FK) */
    private Long cartItemId;

    /** 상품 옵션 ID (product_option FK) */
    private Long productOptionId;

    /** 추가 금액 */
    private Integer extraPrice;
}
