package com.app.babybaby.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomKeyQueryDslImpl implements RandomKeyQueryDsl {
    private final JPAQueryFactory query;
}
