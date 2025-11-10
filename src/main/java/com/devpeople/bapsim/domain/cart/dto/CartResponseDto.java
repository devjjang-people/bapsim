package com.devpeople.bapsim.domain.cart.dto;

import java.time.LocalDateTime;

/**
 * 장바구니 조회 Response DTO
 * FE로 내려갈 출력 모델
 */
public record CartResponseDto(
        Long cartId,
        Long userId,
        Long storeId,
        LocalDateTime createdAt
) {
}
