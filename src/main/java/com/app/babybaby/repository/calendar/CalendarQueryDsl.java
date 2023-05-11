package com.app.babybaby.repository.calendar;

import com.app.babybaby.entity.calendar.Calendar;

import java.util.List;

public interface CalendarQueryDsl {
    public List<Calendar> findAllScheduleByMemberId_QueryDsl(Long memberId);
}
