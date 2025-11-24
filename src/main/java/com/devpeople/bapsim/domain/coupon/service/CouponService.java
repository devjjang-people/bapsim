package com.devpeople.bapsim.domain.coupon.service;

import com.devpeople.bapsim.domain.coupon.entity.Coupon;
import com.devpeople.bapsim.domain.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    /**
     * 쿠폰 등록
     */
    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    /**
     * 쿠폰 전체 조회
     */
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }

    /**
     * 쿠폰 단건 조회
     */
    public Coupon findById(Long id) {
        return couponRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다."));
    }

    /**
     * 쿠폰 수정
     */

    /**
     * 쿠폰 삭제
     */
    public void delete(Long id) {
        couponRepository.deleteById(id);
    }

}
