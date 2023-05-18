package com.app.babybaby.service.board.review;


import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
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
public class ReviewServiceTests {
    @Autowired
    private ReviewService reviewService;

    @Test
    public void findAllEventsByMemberIdTest(){
        reviewService.findAllEventsByMemberId(1L).stream().map(EventDTO::toString).forEach(log::info);
    }
}
