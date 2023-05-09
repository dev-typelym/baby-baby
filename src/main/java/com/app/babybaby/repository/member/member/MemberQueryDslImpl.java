package com.app.babybaby.repository.member.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberQueryDslImpl implements MemberQueryDsl {
    private final JPAQueryFactory query;
}
