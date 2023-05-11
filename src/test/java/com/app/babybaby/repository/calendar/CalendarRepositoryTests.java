package com.app.babybaby.repository.calendar;

import com.app.babybaby.entity.calendar.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CalendarRepositoryTests {
    @Autowired
    CalendarRepository calendarRepository;

    @Test
    public void findAllScheduleByMemberId_QueryDslTest(){
        calendarRepository.findAllScheduleByMemberId_QueryDsl(1L).stream().map(Calendar::toString).forEach(log::info);
    }
}
