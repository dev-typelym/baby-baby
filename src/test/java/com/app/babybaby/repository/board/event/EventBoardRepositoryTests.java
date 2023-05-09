package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.type.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class EventBoardRepositoryTests {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    MemberRepository memberRepository;


    //  이벤트 게시판 등록
    @Test
    public void saveTest(){
//        Event event = new Event(10L,new Address(),10000L,"Test","Test111", CategoryType.MUSEUM);
        Address address = new Address();
        address.setAddress("서울시");
        address.setAddressDetail("노원구");
        address.setAddressSubDetail("월계동");
        address.setPostcode("1111");

        LocalDateTime registerDate = LocalDateTime.now();

        Member member = new Member("member@example.com", "홍길동", "password", "nickname", "hi sentence", "010-1234-5678", address, "profile_original_name.png", "profile_uuid", "/profile/path", registerDate, MemberType.COMPANY, AcceptanceType.ACCEPTED, SleepType.AWAKE, GuideType.DISABLED, CategoryType.SPORTS, "file_path", "file_uuid", "file_original_name");

        memberRepository.save(member);

        Calendar calendar = new Calendar("요기용", CategoryType.ART,LocalDateTime.now(),LocalDateTime.now());

        Event event = new Event(10L, address, 100000L, "안녕?", CategoryType.MUSEUM,calendar,member);
        eventRepository.save(event);
    }

    //  이벤트 게시판 조회 페이징
//    @Test
//    public void findEventListTest() {
//        Pageable pageable = PageRequest.of(0, 10);
//        eventRepository.findEventList(pageable).stream().map(Event::toString).forEach(log::info);
//    }


    //    이벤트 게시판 상세 (아주 잘됨 ㅋ)
    @Test
    public void findEventByIdTest(){
        eventRepository.findEventById(2L).ifPresent(event -> log.info(event.toString()));
    }











}
