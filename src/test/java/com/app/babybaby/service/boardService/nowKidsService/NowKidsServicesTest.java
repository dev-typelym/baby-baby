package com.app.babybaby.service.boardService.nowKidsService;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class NowKidsServicesTest {
    @Autowired
    NowKidsServices nowKidsServices;

    @Test
    public void findAllTest(){
        nowKidsServices.getAllInfoForListDesc_QueryDsl(1, 1, 1L).stream().map(NowKidsDTO::toString).forEach(log::info);
    }
}
