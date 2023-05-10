package com.app.babybaby.repository.like.nowKidsLike;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NowKidsLikeQueryDslImpl implements NowKidsLikeQueryDsl {
    private final JPAQueryFactory query;
}
