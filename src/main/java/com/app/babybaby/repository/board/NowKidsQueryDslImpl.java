package com.app.babybaby.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NowKidsQueryDslImpl implements NowKidsQueryDsl {
    private final JPAQueryFactory query;
}
