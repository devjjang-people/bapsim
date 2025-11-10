package com.devpeople.bapsim.domain.cart.service;

import com.devpeople.bapsim.domain.cart.entity.Cart;
import com.devpeople.bapsim.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart save(Long userId, Long storeId) {
        Cart cart = Cart.builder()
                .userId(userId)
                .storeId(storeId)
                .build();

        return cartRepository.save(cart);
    }

    public Cart find(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("장바구니를 찾을 수 없습니다."));
    }

    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}
