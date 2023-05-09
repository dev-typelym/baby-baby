package com.app.babybaby.repository.member.crew;

import com.app.babybaby.entity.embeddable.Address;
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
public class CrewRepositoryTests {
//    @Autowired
//    CrewRepository crewRepository;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    KidRepository kidRepository;
//
//
//    @Test
//    public void saveTest(){
//        User user = new User("yen022@naver.com", "rrr", "wjdvy6121!", "asd","asd" ,"01041219495" ,new Address(), LocalDateTime.now(), UserType.GENERAL, AcceptanceType.ACCEPTED, SleepType.AWAKE, GuideType.DISABLED, CategoryType.ART);
//        userRepository.save(user);
//        Kid kid = new Kid("김동한", 11L, GenderType.MAN,user);
//        kidRepository.save(kid);
//    }
//
//    @Test
////    이거는
//    public void findKidByUserIdTest(){
////        pointRepository.findPointByMemberId(2L).stream().map(Point::toString).forEach(log::info);
//        crewRepository.findKidByUserId(1L).stream().map(Kid::toString).forEach(log::info);
//    }

}
