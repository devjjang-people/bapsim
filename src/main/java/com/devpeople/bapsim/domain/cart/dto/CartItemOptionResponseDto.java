package com.devpeople.bapsim.domain.cart.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 장바구니 아이템 옵션 조회 응답 DTO
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemOptionResponseDto {

    /** 옵션 고유 ID */
    private Long id;

    /** 장바구니 아이템 ID */
    private Long cartItemId;

    /** 상품 옵션 ID */
    private Long productOptionId;

    /** 추가 금액 */
    private Integer extraPrice;

    /** 생성일시 */
    private LocalDateTime createdAt;

    /** 수정일시 */
    private LocalDateTime updatedAt;
}
