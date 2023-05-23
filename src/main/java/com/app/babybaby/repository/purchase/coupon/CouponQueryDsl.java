package com.app.babybaby.repository.purchase.coupon;

import com.app.babybaby.entity.purchase.coupon.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CouponQueryDsl {
//    나의 쿠폰 조회
    public Slice<Coupon> findCouponByMemberId_QueryDSL(Pageable pageable, Long memberId);
//     사용하지 않은 쿠폰 조회
    public List<Coupon> findAllUnusedCoupon(Long memberId);
    
//    전체 쿠폰 조회
    public Long totalCouponCount(Long memberId);
    
//    전체 사용하지 않은 쿠폰 조회
    public Long totalUnusedCouponCount(Long memberId);
}
