package com.devpeople.bapsim.domain.coupon.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 쿠폰 관련 테이블
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "coupon")
public class Coupon extends BaseEntity {

    /** 쿠폰 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 쿠폰 코드 ID */
    private String code;

    /** 가게 ID */
    private Long storeId;

    /** 쿠폰 명 */
    private String name;

    /**
     * 쿠폰 타입
     * - FIXED : 정액 할인
     * - PERCENT : 비율 할인
     */
    private String type;

    /** 정액 할인 금액 */
    private Integer amount;

    /** 비율 할인 퍼센트 */
    private Integer percent;

    /** 최대 할인 금액 (최대 적용 조건) */
    private Integer maxDiscount;

    /** 최소 주문 금액 (쿠폰 사용 조건) */
    private Integer minOrderAmt;

    /**
     * 쿠폰 상태
     * - ACTIVE : 발급 및 사용 가능
     * - INACTIVE : 비활성/중지
     * - EXPIRED : 만료
     */
    private String status;

    /** 발급 가능 총 수량 */
    private Integer issuedQty;

    /** 개인당 발급 제한 */
    private Integer perUserLimit;

    /** 쿠폰 사용 가능 시작일 */
    private String startAt;

    /** 쿠폰 사용 가능 종료일 */
    private String endAt;
}
