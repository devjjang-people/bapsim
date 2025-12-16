package com.devpeople.bapsim.domain.payment.entity;

/**
 * 결제 상태
 */
public enum PaymentStatus {

    /** 결제 대기 */
    READY,

    /** 결제 성공 */
    SUCCESS,

    /** 결제 실패 */
    FAIL
}
