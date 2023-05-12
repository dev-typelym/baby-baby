package com.app.babybaby.service.guideSchedule;

import com.app.babybaby.repository.guideSchedule.GuideScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class GuideScheduleTests {
    @Autowired
    GuideScheduleRepository guideScheduleRepository;

    @Test
    public void findGuideScheduleById_QueryDslTest(){
        log.info(guideScheduleRepository.findGuideScheduleById_QueryDsl(1L).toString());
    }
}
