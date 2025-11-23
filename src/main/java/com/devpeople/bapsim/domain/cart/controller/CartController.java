package com.devpeople.bapsim.domain.cart.controller;

import com.devpeople.bapsim.domain.cart.dto.CartResponseDto;
import com.devpeople.bapsim.domain.cart.entity.Cart;
import com.devpeople.bapsim.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 전용 장바구니 컨트롤러
 */
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 장바구니 생성
     */
    @PostMapping
    public ResponseEntity<CartResponseDto> createCart(
            @RequestParam Long userId,
            @RequestParam Long storeId
    ) {
        Cart cart = cartService.createOrGetCart(userId, storeId);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .storeId(cart.getStoreId())
                .createdAt(cart.getCreateAt())
                .updatedAt(cart.getUpdateAt())
                .build();

        return ResponseEntity.ok(cartResponseDto);
    }

    /**
     * 장바구니 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long id) {


        return null;
    }
}
