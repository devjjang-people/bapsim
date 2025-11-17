package com.devpeople.bapsim.domain.cart.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 *  장바구니 조회 응답 DTO
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class CartResponseDto {


    /** 장바구니 고유 ID */
    private Long id;

    /** 사용자 ID */
    private Long userId;

    /** 가게 ID */
    private Long storeId;

    /** 생성일시 */
    private LocalDateTime createdAt;

    /** 수정일시 */
    private LocalDateTime updatedAt;
}
