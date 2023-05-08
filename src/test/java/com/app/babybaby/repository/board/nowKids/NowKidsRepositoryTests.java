package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.user.User;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.user.user.UserRepository;
import com.app.babybaby.type.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NowKidsRepositoryTests {
    @Autowired
    NowKidsRepository nowKidsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Test
    public void saveTest(){
        Address address = new Address();
        address.setAddress("분당");
        address.setAddressDetail("d");
        address.setAddressSubDetail("dfa");
        address.setPostcode("12342132");
        Calendar calendar = new Calendar("이벤트1", CategoryType.AGRICULTURE, LocalDateTime.now(), LocalDateTime.now());
        Event event = new Event("Test1","test",10L,address,10000L,"TEST","TEst",CategoryType.MUSEUM, calendar);
        User user = new User("you@naver.com", "정유찬", "1234", "Bool", "안녕하세요",
                "01012341234", address, LocalDateTime.now(), UserType.COMPANY, AcceptanceType.ACCEPTED, SleepType.AWAKE, GuideType.NON_DISABLED,CategoryType.AGRICULTURE);
        userRepository.save(user);
        eventRepository.save(event);
        NowKids nowKids = new NowKids(event, user);
        nowKidsRepository.save(nowKids);
    }

    @Test @Transactional
    public void findAllTest() {
       log.info(nowKidsRepository.findAll().toString());
    }

    @Test
    public void findByIdTest(){
        log.info(nowKidsRepository.findNowKidsEventByDate().toString());
    }

    @Test
    public void findByIdTest2(){
        log.info(nowKidsRepository.findById(1L).toString());
    }


}
