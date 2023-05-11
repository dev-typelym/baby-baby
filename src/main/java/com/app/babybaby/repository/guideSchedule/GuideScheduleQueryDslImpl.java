package com.app.babybaby.repository.guideSchedule;

import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.guideSchedule.GuideSchedule;
import com.app.babybaby.entity.guideSchedule.QGuideSchedule;
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

    
//    해당 가이드의 아이디로 가이드 스케쥴 가져오기
    @Override
    public List<GuideSchedule> findGuideScheduleById_QueryDsl(Long userId) {
        return query.selectFrom(guideSchedule)
                .join(guideSchedule.calendar)
                .join(guideSchedule.member)
                .where(guideSchedule.member.id.eq(userId))
                .fetch()
                ;
    }

}
