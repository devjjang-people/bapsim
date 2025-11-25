package com.devpeople.bapsim.domain.cart.repository;

import com.devpeople.bapsim.domain.cart.entity.CartItemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemOptionRepository extends JpaRepository<CartItemOption, Long> {

    /**
     * 특정 CartItem 에 연결된 모든 옵션 조회
     */
    List<CartItemOption> findAllByCartItemId(Long cartItemId);

    /**
     * 동일한 옵션이 있는지 중복 체크
     */
    Optional<CartItemOption> findByCartItemIdAndProductOptionId(Long cartItemId, Long productOptionId);

    /**
     * CartItem 삭제시 옵션도 함께 삭제
     */
    void deleteAllByCartItemId(Long cartItemId);
}
