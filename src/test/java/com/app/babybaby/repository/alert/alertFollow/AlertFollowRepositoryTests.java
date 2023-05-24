package com.app.babybaby.repository.alert.alertFollow;

import com.app.babybaby.service.alert.alertFollow.AlertFollowService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AlertFollowRepositoryTests {
    @Autowired
    AlertFollowRepository alertFollowRepository;

    @Autowired
    AlertFollowService alertFollowService;

    @Test
    public void test11(){
        log.info(alertFollowRepository.find8RecentFollowersByMemberId(1L).toString());
    }

    @Test
    public void test2(){
        log.info(alertFollowService.find8RecentFollowersByMemberId(2L).toString());
    }
}
