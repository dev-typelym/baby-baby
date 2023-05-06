package com.app.babybaby.repository.user.follow;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FollowQueryDslImpl implements FollowQueryDsl {
    private final JPAQueryFactory query;
}
