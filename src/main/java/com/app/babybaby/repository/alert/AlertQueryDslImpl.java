package com.app.babybaby.repository.alert;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlertQueryDslImpl implements AlertQueryDsl {
    private final JPAQueryFactory query;
}
