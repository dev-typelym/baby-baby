package com.app.babybaby.service.member.crew;

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
public class CrewServiceTests {

    @Autowired
    CrewService crewService;


    @Test
    public void findCrewByMemberIdTest(){
//        log.info(crewService.findCrewByMemberId(110L, "2023-05-24-15:45:30.00").toString());
    }


}
