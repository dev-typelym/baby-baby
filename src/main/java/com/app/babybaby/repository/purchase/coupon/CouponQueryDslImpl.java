package com.app.babybaby.repository.purchase.coupon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponQueryDslImpl implements CouponQueryDsl {
    private final JPAQueryFactory query;
}
