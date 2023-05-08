package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.type.CategoryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
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

    //  이벤트 게시판 등록
    @Test
    public void saveTest(){
//        Event event = new Event(10L,new Address(),10000L,"Test","Test111", CategoryType.MUSEUM);
        Address address = new Address();
        address.setAddress("서울시");
        address.setAddressDetail("노원구");
        address.setAddressSubDetail("월계동");
        address.setPostcode("1111");

        Calendar calendar = new Calendar("요기용", CategoryType.ART,LocalDateTime.now(),LocalDateTime.now());
        
        Event event = new Event("Test1","test",10L,address,10000L,"TEST","TEst",CategoryType.MUSEUM, calendar);

        eventRepository.save(event);
    }

    //  이벤트 게시판 조회 페이징
    @Test
    @Transactional
    public void findEventListTest() {
        org.springframework.data.domain.Pageable pageable = PageRequest.of(0, 10);

        eventRepository.findEventList(pageable).stream().map(Event::toString).forEach(log::info);
    }









}
