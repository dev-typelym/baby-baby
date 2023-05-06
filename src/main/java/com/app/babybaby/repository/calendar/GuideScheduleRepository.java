package com.app.babybaby.repository.calendar;

import com.app.babybaby.entity.calendar.GuideSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideScheduleRepository extends GuideScheduleQueryDsl, JpaRepository<GuideSchedule, Long> {
}
