package com.devpeople.bapsim.domain.cart.dto;

/**
 * CartItemOption 생성 Request DTO
 */
public record CartItemOptionCreateRequestDto(
        Long cartItemId,
        Long productOptionId,
        Integer extraPrice
) {}