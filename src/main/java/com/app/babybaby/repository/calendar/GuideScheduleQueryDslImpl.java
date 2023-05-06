package com.app.babybaby.repository.calendar;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GuideScheduleQueryDslImpl implements GuideScheduleQueryDsl {
    private final JPAQueryFactory query;
}
