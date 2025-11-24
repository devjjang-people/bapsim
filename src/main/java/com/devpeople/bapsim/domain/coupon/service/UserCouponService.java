package com.devpeople.bapsim.domain.coupon.service;

import com.devpeople.bapsim.domain.coupon.entity.UserCoupon;
import com.devpeople.bapsim.domain.coupon.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCouponService {

    private  final UserCouponRepository userCouponRepository;

    /**
     * 사용자 쿠폰 저장
     */
    public UserCoupon save(UserCoupon userCoupon) {

        return  userCouponRepository.save(userCoupon);
    }

    /**
     * 사용자 쿠폰 조회
     */
    public UserCoupon findById(Long userId) {
        return userCouponRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("사용자가 현재 보유하고있는 쿠폰이 없습니다."));
    }

    /**
     * 사용자 쿠폰 삭제
     */
    public void delete(Long id) {
        userCouponRepository.deleteById(id);
    }

}
