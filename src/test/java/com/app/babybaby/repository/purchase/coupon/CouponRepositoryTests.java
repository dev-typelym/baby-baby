package com.app.babybaby.repository.purchase.coupon;

import com.app.babybaby.entity.purchase.coupon.Coupon;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CouponRepositoryTests {
    @Autowired
    CouponRepository couponRepository;

    //    memberId 로 나의 쿠폰조회
    @Test
    public void findCouponByMemberIdTest(){
        Pageable pageable = PageRequest.of(0, 5);

        couponRepository.findCouponByMemberId(pageable,1L).stream().map(Coupon::toString).forEach(log::info);
    }

    @Test
    public void countTest(){
        couponRepository.findAllByMemberId(1L).stream().map(Coupon::toString).forEach(log::info);
    }

}
