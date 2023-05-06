package com.app.babybaby.repository.guideSchedule;

import com.app.babybaby.entity.guideSchedule.GuideSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideScheduleRepository extends GuideScheduleQueryDsl, JpaRepository<GuideSchedule, Long> {
}
