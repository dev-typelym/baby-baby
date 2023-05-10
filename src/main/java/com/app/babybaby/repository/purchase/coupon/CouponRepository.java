package com.app.babybaby.repository.purchase.coupon;

import com.app.babybaby.entity.purchase.coupon.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponQueryDsl {

    @Query("select c from Coupon c where c.member.id = :memberId")
    public Slice<Coupon> findAllByMemberId(Long memberId);
}
