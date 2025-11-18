package com.devpeople.bapsim.domain.cart.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 장바구니 아이템 조회 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CartItemResponseDto {


    /** 장바구니 아이템 고유 ID */
    private Long id;

    /** 장바구니 ID */
    private Long cartId;

    /** 상품 ID */
    private Long productId;

    /** 수량 */
    private Integer quantity;

    /** 단가 */
    private Integer unitPrice;

    /** 요청사항 */
    private String note;

    /** 생성일시 */
    private LocalDateTime createdAt;

    /** 수정일시 */
    private LocalDateTime updatedAt;
}
