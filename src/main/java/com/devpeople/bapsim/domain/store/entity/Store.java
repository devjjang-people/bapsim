package com.devpeople.bapsim.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 가게 테이블
 * */

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    /** 기본키 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 가게 이름 */
    @Column(length = 150, nullable = false)
    private String name;

    /** 음식 카테고리 (예: 한식, 중식, 치킨 등) */
    @Column(length = 50)
    private String category;

    /** 평균 평점 / NUMERIC(3,2) */
    @Column(name = "rating_avg", precision = 3, scale = 2, nullable = false)
    private BigDecimal ratingAvg;

    /** 리뷰 개수 */
    @Column(name = "review_count", nullable = false)
    private Integer reviewCount;

    /** 최소 주문 금액 */
    @Column(name = "min_order_amt", nullable = false)
    private Integer minOrderAmt;

    /** 영업 여부 (삭제 ?) */
    @Column(name = "is_open", nullable = false)
    private Boolean isOpen = true;

    /** 등록일시 */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /** 수정일시 */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /** 삭제일시 */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
