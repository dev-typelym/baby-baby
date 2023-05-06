package com.app.babybaby.repository.calendar;

import com.app.babybaby.repository.guideSchedule.GuideScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class GuideScheduleRepositoryTests {
    @Autowired
    GuideScheduleRepository guideScheduleRepository;
}
