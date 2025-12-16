package com.devpeople.bapsim.domain.cart.service;

import com.devpeople.bapsim.domain.cart.entity.Cart;
import com.devpeople.bapsim.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 장바구니 서비스
 * [기능 정리]
 *  - 유저별 1개의 가게 장바구니 유지
 *  - 같은 가게 상품만 담을수 있음 (다른 가게 상품을 담으려고 할 경우 에러처리)
 *  - 장바구니 생성/조회/삭제 기능
 */
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    /**
     * 장바구니 생성 (유저 + 가게 기준)
     * - 한 유저는 한 가게에 대해 장바구니 1개만 생성할수있음
     */
    public Cart createOrGetCart(Long userId, Long storeId) {

        // 이미 같은 가게 장바구니가 존재하는 경우 반환
        return cartRepository.findByUserIdAndStoreId(userId, storeId)
                .orElseGet(() -> {
                    Cart cart = Cart.builder()
                            .userId(userId)
                            .storeId(storeId)
                            .build();
                    return cartRepository.save(cart);
                });
    }

    /**
     * 장바구니 조회
     */
    public Cart get(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("장바구니를 찾을 수 없습니다."));
    }

    /**
     * 장바구니 삭제
     */
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}
