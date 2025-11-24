package com.devpeople.bapsim.domain.cart.dto;

import lombok.*;

/**
 * 장바구니 아이템 생성/수정 요청 DTO
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CartItemRequestDto {

    /** 장바구니 ID (cartId) */
    private Long cartId;

    /** 상품 ID (productId) */
    private Long productId;

    /** 수량 */
    private Integer quantity;

    /** 단가 (상품 금액) */
    private Integer unitPrice;

    /** 요청사항 */
    private String note;

}
