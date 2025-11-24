package com.devpeople.bapsim.domain.cart.dto;

import lombok.*;

/**
 * 장바구니 생성/수정 요청 DTO
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRequestDto {

    /** 사용자 ID */
    private Long userId;

    /** 가게 ID */
    private Long storeId;
}
