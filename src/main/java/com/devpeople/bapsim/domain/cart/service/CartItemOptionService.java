package com.devpeople.bapsim.domain.cart.service;

import com.devpeople.bapsim.domain.cart.entity.CartItemOption;
import com.devpeople.bapsim.domain.cart.repository.CartItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 장바구니 아이템 옵션 서비스
 */

@Service
@RequiredArgsConstructor
public class CartItemOptionService {

    private CartItemOptionRepository cartItemOptionRepository;

    /**
     * 장바구니 아이템에 옵션 추가
     */
    public CartItemOption addCartItemOption(Long cartItemId, Long productOptionId, Integer extraPrice) {

        // 먼저 옵션이 있는지 확인
        cartItemOptionRepository.findByCartItemIdAndProductOptionId(cartItemId, productOptionId)
                .ifPresent(o -> {
                    throw new IllegalArgumentException("이미 선택한 옵션입니다.");
                });

        CartItemOption cartItemOption = CartItemOption.builder()
                .cartItemId(cartItemId)
                .productOptionId(productOptionId)
                .extraPrice(extraPrice)
                .build();

        return  cartItemOptionRepository.save(cartItemOption);
    }

    /**
     * 특정 아이템에 대한 모든 옵션 조회
     */
    public List<CartItemOption> getOptions(Long cartItemId) {
        return cartItemOptionRepository.findAllByCartItemId(cartItemId);
    }

    /**
     * 특정 CartItem 에 연결된 옵션 전체 삭제
     * (cartItem 삭제시)
     */
    public void deleteAll(Long cartItemId) {
        cartItemOptionRepository.deleteAllByCartItemId(cartItemId);
    }

    /**
     * 특정 옵션만 삭제
     */
    public void delete(Long optionId) {
        cartItemOptionRepository.deleteById(optionId);
    }
}
