package com.app.babybaby.repository.purchase;

import com.app.babybaby.entity.purchase.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponQueryDsl {
}
