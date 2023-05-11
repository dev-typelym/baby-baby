package com.app.babybaby.repository.guideSchedule;

import com.app.babybaby.entity.calendar.Calendar;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.babybaby.entity.guideSchedule.QGuideSchedule.guideSchedule;

@RequiredArgsConstructor
public class GuideScheduleQueryDslImpl implements GuideScheduleQueryDsl {
    private final JPAQueryFactory query;

//    가이드 스케줄 조회 목록
    @Override
    public List<Calendar> findCalendarByUserId(Long userId) {
        return query.select(guideSchedule.calendar).from(guideSchedule).where(guideSchedule.member.id.eq(userId)).orderBy(guideSchedule.id.desc()).fetch();
    }

}
