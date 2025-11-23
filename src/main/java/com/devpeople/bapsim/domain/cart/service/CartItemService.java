package com.devpeople.bapsim.domain.cart.service;

import com.devpeople.bapsim.domain.cart.entity.Cart;
import com.devpeople.bapsim.domain.cart.entity.CartItem;
import com.devpeople.bapsim.domain.cart.repository.CartItemRepository;
import com.devpeople.bapsim.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 장바구니 아이템 서비스
 * - 같은 상품은 중복 추가x -> 수량만 증가
 *  - 다른 가게 메뉴는 장바구니에 담을 수 없음
 *  - totalPrice(
 */
@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;

    /**
     * 장바구니에 상품 추가
     * - 같은 메뉴 있을시 quantity 증가
     * - 다른 가게 메뉴 추가 불가
     */
    public CartItem addItem(Long userId, Long storeId, Long productId, Integer quantity, Integer unitPrice, String note) {

        // 장바구니 없으면 유저 + 가게 기준으로 생성
        Cart cart = cartService.createOrGetCart(userId, storeId);

        // 기존 동일 상품있는지 체크
        Optional<CartItem> existing = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        // 가지고 있다면 수량만 증가
        if(existing.isPresent()) {
            CartItem cartItem = existing.get();
        }

        return null;
    };

    public CartItem get(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템을 찾을 수 없습니다."));
    }

    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
