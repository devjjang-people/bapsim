package com.devpeople.bapsim.domain.coupon.repository;

import com.devpeople.bapsim.domain.coupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
