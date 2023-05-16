package com.app.babybaby.service.board.nowKids;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
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
public class NowKidsServiceTest {
    @Autowired
    NowKidsService nowKidsService;

    @Test
    public void findAllTest(){
//        nowKidsService.getAllInfoForListDesc(5, 1).stream().map(NowKidsDTO::toString).forEach(log::info);
        log.info( nowKidsService.getAllInfoForListDesc(3, 2).toString());
    }


    @Test
    public void getBoardAndCalendarByGeneralGuideIdTest(){
        log.info(nowKidsService.getBoardAndCalendarByGeneralGuideId(1L).toString());
    }

    @Test
    public void find8AuthorTest(){
        nowKidsService.find8AuthorDesc().stream().map(MemberDTO::toString).forEach(log::info);
    }
}
