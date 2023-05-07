package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.user.User;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.type.CategoryType;
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

    @Test
    public void saveTest(){
        Address address = new Address();
        address.setAddress("분당");
        address.setAddressDetail("d");
        address.setAddressSubDetail("dfa");
        address.setPostcode("12342132");
        Calendar calendar = new Calendar("이벤트1", CategoryType.AGRICULTURE, LocalDateTime.now(), LocalDateTime.now());

//        Event event = new Event(10L, address, 10000L, "놀러가요", "안녕하세요", CategoryType.ART, calendar, );
    }

    @Test @Transactional
    public void findAllTest() {
        List<NowKids> nowKidsList = nowKidsRepository.findAll();
        for (NowKids nowKids : nowKidsList) {
            log.info(nowKids.getEvent().toString());
            log.info(nowKids.getGuide().toString());
        }
    }

    @Test
    public void findByIdTest(){
        log.info(nowKidsRepository.findById(1L).toString());
    }


}
