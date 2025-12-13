package com.devpeople.bapsim.domain.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 결제 엔티티
 * - 주문(Order)과 1:1 관계
 * - 실제 결제 결과를 저장하는 테이블
 */
@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    /** 결제 PK */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 주문 FK
     * - order 테이블과 1:1 관계
     * - 하나의 주문은 하나의 결제만 가질 수 있음
     */
    @Column(nullable = false, unique = true)
    private Long orderId;

    /**
     * PG사
     * 예: KAKAO_PAY, NAVER_PAY, TOSS_PAY
     */
    @Column(length = 30, nullable = false)
    private String provider;

    /**
     * 결제 수단
     * 예: CARD, PAY, BANK_TRANSFER
     */
    @Column(length = 30, nullable = false)
    private String method;

    /**
     * PG 거래 ID
     * - PG 연동 시 발급되는 고유 ID
     * - 결제 성공 후에만 값이 들어갈 수 있음
     */
    @Column(length = 100, unique = true)
    private String transactionId;

    /**
     * 실제 결제 금액
     * - 쿠폰, 할인 적용이 끝난 "최종 결제 금액"
     */
    @Column(nullable = false)
    private Integer amount;

    /**
     * 결제 상태
     * READY / SUCCESS / FAIL
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private PaymentStatus status;

    /**
     * 결제 승인 시각
     * - 결제 성공 시에만 값이 존재
     */
    private LocalDateTime approvedAt;
}
