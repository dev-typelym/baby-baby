package com.app.babybaby.service.member.follow;

import com.app.babybaby.repository.member.follow.FollowRepository;
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
public class FollowServiceTests {
    @Autowired
    FollowRepository followRepository;

    @Autowired
    FollowService followService;

    @Test
    public void findAllParentsBoardByMemberId_QueryDSLTest(){
        followRepository.findAllParentsBoardByMemberId_QueryDSL(1L);
    }

    @Test
    public void deleteFollowTest(){
        followService.deleteFollow(7L, 13L);
    }
}
