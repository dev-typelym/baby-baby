package com.app.babybaby.repository.user.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserQueryDslImpl implements UserQueryDsl {
    private final JPAQueryFactory query;
}
