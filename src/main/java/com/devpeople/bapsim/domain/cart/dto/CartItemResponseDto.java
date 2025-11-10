package com.devpeople.bapsim.domain.cart.dto;


import java.time.LocalDateTime;

/**
 * CartItem 조회 Response DTO
 */
public record CartItemResponseDto(
        Long cartItemId,
        Long cartId,
        Long productId,
        Integer quantity,
        Integer unitPrice,
        String note,
        LocalDateTime createdAt
) {}
