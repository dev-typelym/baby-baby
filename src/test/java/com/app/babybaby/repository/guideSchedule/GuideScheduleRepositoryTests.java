package com.app.babybaby.repository.guideSchedule;

import com.app.babybaby.repository.member.crew.CrewRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class GuideScheduleRepositoryTests {
    @Autowired
    GuideScheduleRepository guideScheduleRepository;
    @Autowired
    CrewRepository crewRepository;

    @Test
    public void tt(){
        Pageable pageable = PageRequest.of(0, 10);
        LocalDateTime date = LocalDateTime.of(2023, Month.MAY, 11, 10, 11, 41, 957000000);

//        crewRepository.findCrewByMemberId(1L, )
    }


}
