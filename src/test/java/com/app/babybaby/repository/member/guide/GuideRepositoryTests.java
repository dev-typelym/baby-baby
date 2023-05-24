package com.app.babybaby.repository.member.guide;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.guideSchedule.GuideSchedule;
import com.app.babybaby.entity.member.Guide;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.calendar.CalendarRepository;
import com.app.babybaby.repository.guideSchedule.GuideScheduleRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.type.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class GuideRepositoryTests {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    GuidRepository guideRepository;
    @Autowired
    GuideScheduleRepository guideScheduleRepository;
    @Autowired
    CalendarRepository calendarRepository;

    @Test
    public void saveTest(){
        GuideSchedule guideSchedule = new GuideSchedule(calendarRepository.findById(3L).get(),eventRepository.findById(2L).get(),memberRepository.findById(1L).get());
        guideScheduleRepository.save(guideSchedule);

//        guideRepository.save()
    }

    @Test
    public void findByIdTest(){
        guideScheduleRepository.findCalendarByUserId(4L).stream().map(Calendar::toString).forEach(log::info);
    }

    @Test
    public void findEventByIdTest(){
        guideRepository.findEventById(6L, 2L).stream().map(Guide::toString).forEach(log::info);
    }

    @Test
    public void findTopByEventIdAndAvailableTypeEndsWithTest(){
        log.info(guideRepository.findFirstByEventIdAndAvailableTypeOrderById(273L, GuideAvailableType.AVAILABLE).toString());
    }
    @Test
    public void findFirstByGeneralGuideNotTest(){
        log.info(guideRepository.findFirstByGeneralGuideIsNullAndEvent_Id(2L).toString());
    }

    @Test
    public void existsByEvent_IdTest(){
        log.info(String.valueOf(guideRepository.existsByEvent_Id(7L)));
    }

}
