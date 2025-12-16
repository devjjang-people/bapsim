package com.devpeople.bapsim.domain.payment.repository;

import com.devpeople.bapsim.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 결제 Repository
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * 주문 ID 기준 결제 조회
     * - Order : Payment = 1 : 1
     */
    Optional<Payment> findByOrderId(Long orderId);
}
