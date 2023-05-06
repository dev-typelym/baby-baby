package com.app.babybaby.repository.purchase;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PurchaseQueryDslImpl implements PurchaseQueryDsl {
    private final JPAQueryFactory query;
}
