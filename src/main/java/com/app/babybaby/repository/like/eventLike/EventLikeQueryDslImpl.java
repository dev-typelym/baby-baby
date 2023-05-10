package com.app.babybaby.repository.like.eventLike;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventLikeQueryDslImpl implements EventLikeQueryDsl {
    private final JPAQueryFactory query;
}
