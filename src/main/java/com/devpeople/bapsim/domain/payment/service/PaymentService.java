package com.devpeople.bapsim.domain.payment.service;

import com.devpeople.bapsim.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 결제 서비스
 */
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    /**
     * 결제 생성 (결제 시도)
     */


}
