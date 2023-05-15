package com.app.babybaby.service.like.nowKidsLike;

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
public class NowKidsLikeServiceTests {
    @Autowired
    NowKidsLikeService nowKidsLikeService;

    @Test
    public void likeSaveTest(){
        nowKidsLikeService.likeSave(28L, 1L);
    }

    @Test
    public void deleteLikeTest(){
        nowKidsLikeService.deleteLike(28L, 1L);
    }
}
