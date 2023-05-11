package com.app.babybaby.repository.calendar;

import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.calendar.QCalendar;
import com.app.babybaby.entity.guideSchedule.QGuideSchedule;
import com.app.babybaby.entity.member.QGuide;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.babybaby.entity.calendar.QCalendar.calendar;
import static com.app.babybaby.entity.guideSchedule.QGuideSchedule.guideSchedule;
import static com.app.babybaby.entity.member.QGuide.guide;

@RequiredArgsConstructor
public class CalendarQueryDslImpl implements CalendarQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public List<Calendar> findAllScheduleByMemberId_QueryDsl(Long memberId) {
        return query.select(guideSchedule.calendar)
                .from(guideSchedule)
                .join(guideSchedule.calendar)
                .join(guideSchedule.member)
                .where(guideSchedule.member.id.eq(memberId))
                .fetch()
                ;
    }
}
