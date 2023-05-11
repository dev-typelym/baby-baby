package com.app.babybaby.repository.guideSchedule;

import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.guideSchedule.GuideSchedule;
import com.app.babybaby.entity.guideSchedule.QGuideSchedule;

import java.util.List;

public interface GuideScheduleQueryDsl {
//    가이드 스케쥴 조회
    public List<Calendar> findCalendarByUserId(Long userId);

    public List<GuideSchedule> findGuideScheduleById_QueryDsl(Long userId);
}
