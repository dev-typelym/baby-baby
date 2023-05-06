package com.app.babybaby.repository.purchase;

import com.app.babybaby.repository.purchase.coupon.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CouponRepositoryTests {
    @Autowired
    CouponRepository couponRepository;
}
