package com.devpeople.bapsim.domain.review.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 리뷰(Review) 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {

    /**
     * 리뷰 고유 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 리뷰를 작성한 사용자 ID
     */
    private Long userId;

    /**
     * 리뷰가 속한 주문 ID
     */
    private Long orderId;

    /**
     * 리뷰 대상 가게 ID
     */
    private Long storeId;

    /**
     * 리뷰 대상 상품 ID
     */
    private Long productId;

    /**
     * 평점 (별 1개~5개)
     */
    private Integer rating;

    /**
     * 리뷰 내용
     */
    private String content;

    /**
     * 리뷰 첨부 이미지(경로)
     */
    private String images;

    /**
     * 수정 여부
     */
    private Boolean isEdited = false;
}
