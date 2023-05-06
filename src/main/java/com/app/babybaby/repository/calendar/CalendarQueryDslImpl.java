package com.app.babybaby.repository.calendar;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalendarQueryDslImpl implements CalendarQueryDsl {
    private final JPAQueryFactory query;
}
