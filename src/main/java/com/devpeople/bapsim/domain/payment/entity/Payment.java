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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    /** 결제 PK */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 주문 FK (주문당 결제 1건) */
    @Column(nullable = false, unique = true)
    private Long orderId;

    /** PG사 (KAKAO_PAY 등) */
    @Column(nullable = false, length = 30)
    private String provider;

    /** 결제 수단 (CARD, PAY 등) */
    @Column(nullable = false, length = 30)
    private String method;

    /** PG 거래 ID (결제 성공 시 세팅) */
    @Column(length = 100, unique = true)
    private String transactionId;

    /** 최종 결제 금액 */
    @Column(nullable = false)
    private Integer amount;

    /** 결제 상태 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    /** 결제 승인 시각 */
    private LocalDateTime approvedAt;
}
