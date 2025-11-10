package com.devpeople.bapsim.domain.cart.service;

import com.devpeople.bapsim.domain.cart.entity.CartItem;
import com.devpeople.bapsim.domain.cart.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItem save(Long cartId, Long productId, Integer quantity, Integer unitPrice, String note) {

        CartItem item = CartItem.builder()
                .cartId(cartId)
                .productId(productId)
                .quantity(quantity)
                .unitPrice(unitPrice)
                .note(note)
                .build();

        return cartItemRepository.save(item);
    }

    public CartItem get(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템을 찾을 수 없습니다."));
    }

    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
