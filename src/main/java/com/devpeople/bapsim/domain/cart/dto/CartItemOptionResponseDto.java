package com.devpeople.bapsim.domain.cart.dto;

/**
 * CartItemOption 조회 Response DTO
 */
public record CartItemOptionResponseDto(
        Long cartItemOptionId,
        Long cartItemId,
        Long productOptionId,
        Integer extraPrice
) {}
