package com.devpeople.bapsim.domain.review.repository;

import com.devpeople.bapsim.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * 특정 사용의 리뷰 전체 조회
     */
    List<Review> findByUserId(Long userId);

    /**
     * 특정 상품에 대한 리뷰 목록 조회
     */
    List<Review> findByProductId(Long productId);

    /**
     * 특정 주문에 대한 리뷰 조회
     */
    Review findByOrderId(Long orderId);

}
