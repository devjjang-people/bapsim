package com.devpeople.bapsim.domain.cart.dto;

/**
 * CartItem 생성 Request DTO
 */
public record CartItemCreateRequestDto(
         Long cartId,
         Long productId,
         Integer quantity,
         Integer unitPrice,
         String note
) {}
