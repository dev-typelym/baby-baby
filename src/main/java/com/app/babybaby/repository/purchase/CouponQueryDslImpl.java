package com.app.babybaby.repository.purchase;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponQueryDslImpl implements CouponQueryDsl {
    private final JPAQueryFactory query;
}
