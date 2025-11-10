package com.devpeople.bapsim.domain.cart.dto;


/**
 * 장바구니 생성 Request DTO
 * Controller → Service 입력 모델
 */
public record CartCreateRequestDto(
         Long userId,
         Long storeId
) {}
