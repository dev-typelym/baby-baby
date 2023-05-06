package com.app.babybaby.repository.user.kid;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KidQueryDslImpl implements KidQueryDsl {
    private final JPAQueryFactory query;
}
