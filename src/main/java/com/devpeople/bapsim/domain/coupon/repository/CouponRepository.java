package com.devpeople.bapsim.domain.coupon.repository;

import com.devpeople.bapsim.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
