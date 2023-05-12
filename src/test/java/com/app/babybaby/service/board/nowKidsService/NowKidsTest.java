package com.app.babybaby.service.board.nowKidsService;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
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
public class NowKidsTest {
    @Autowired
    NowKids nowKids;

    @Test
    public void findAllTest(){
        nowKids.getAllInfoForListDesc(1, 1, 1L).stream().map(NowKidsDTO::toString).forEach(log::info);
    }
}
