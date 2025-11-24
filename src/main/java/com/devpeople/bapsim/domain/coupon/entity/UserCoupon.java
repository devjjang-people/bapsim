package com.devpeople.bapsim.domain.coupon.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 사용자가 보유한 쿠폰 정보를 관리하는 테이블
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_coupon")
public class UserCoupon extends BaseEntity {

    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사용자 ID
     */
    private Long userId;

    /**
     * 쿠폰 ID
     */
    private Long couponId;

    /**
     * 쿠폰 상태
     */
    private String status;

    /**
     * 주문 ID (선택적)
     * 쿠폰이 사용된 주문을 추적하기 위한 필드
     */
    private Long orderId;

    /**
     * 쿠폰 사용 시점
     */
    private String usedAt;
}
