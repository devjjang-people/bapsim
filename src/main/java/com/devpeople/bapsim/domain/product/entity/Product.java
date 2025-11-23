package com.devpeople.bapsim.domain.product.entity;

import com.devpeople.bapsim.domain.store.entity.Store;
import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 상품 테이블
 * */

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product extends BaseEntity {
    /** 기본키 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 스토어 외래키 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    /** 상품명 (메뉴명) */
    @Column(length = 150, nullable = false)
    private String name;

    /** 상품 설명 */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** 상품 기본 가격 */
    @Column(nullable = false)
    private Integer price;

    /** 상품 평균 평점 / NUMERIC(3,2) */
    @Column(name = "rating_avg", precision = 3, scale = 2, nullable = false)
    private BigDecimal ratingAvg;

    /** 상품 리뷰 개수 */
    @Column(name = "review_count", nullable = false)
    @Builder.Default
    private Integer reviewCount = 0;

    /** 판매 상태 (TRUE = 판매중, FALSE = 비활성화) */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    /** 상품 삭제 여부 */
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
