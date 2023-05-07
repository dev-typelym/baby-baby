package com.app.babybaby.repository.like.nowKidsLike;

import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.like.nowKids.NowKidsLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NowKidsLikeRepositoryTests {
    @Autowired
    NowKidsLikeRepository nowKidsLikeRepository;
}
