package com.devpeople.bapsim.domain.cart.service;

import com.devpeople.bapsim.domain.cart.entity.Cart;
import com.devpeople.bapsim.domain.cart.entity.CartItem;
import com.devpeople.bapsim.domain.cart.repository.CartItemRepository;
import com.devpeople.bapsim.domain.cart.repository.CartRepository;
import com.devpeople.bapsim.domain.product.entity.Product;
import com.devpeople.bapsim.domain.product.repository.ProductRepository;
import com.devpeople.bapsim.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 장바구니 아이템 서비스
 * - 같은 상품은 중복 추가x -> 수량만 증가
 *  - 다른 가게 메뉴는 장바구니에 담을 수 없음
 */
@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;

    /**
     * 장바구니에 상품 추가
     * - 같은 메뉴 있을시 quantity 증가
     * - 다른 가게 메뉴 추가 불가
     */
    public CartItem addItem(Long userId, Long storeId, Long productId, Integer quantity, String note) {

        // 상품조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        // 상품 판매 상태 값 체크
        if (!product.getIsActive().equals("is_active")) {
            throw new IllegalArgumentException("현재 구매할 수 없는 상품입니다.");
        }

        // 장바구니 없으면 유저 + 가게 기준으로 생성
        Cart cart = cartService.createOrGetCart(userId, storeId);

        // 상품의 storeId가 장바구니의 storeId와 같은지 체크
        Long productStoreId = product.getStore().getId();
        if (!productStoreId.equals(cart.getStoreId())) {
            throw new IllegalArgumentException("다른 가게의 상품은 장바구니에 담을 수 없습니다.");
        }

        // 기존 동일 상품있는지 체크
        Optional<CartItem> existing = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        // 가지고 있다면 수량만 증가
        if(existing.isPresent()) {
            CartItem cartItem = existing.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);

            return cartItemRepository.save(cartItem);
        }

        // 새 장바구니 아이템 생성
        CartItem newItem = CartItem.builder()
                .cartId(cart.getId())
                .productId(productId)
                .quantity(quantity)
                .unitPrice(product.getPrice())
                .note(note)
                .build();

        return cartItemRepository.save(newItem);
    };

    /**
     * 장바구니 아이템 조회
     */
    public CartItem get(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템을 찾을 수 없습니다."));
    }

    /**
     * 장바구니 아이템 삭제
     */
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
