package com.app.babybaby.service.member.guide;

import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.member.kid.KidRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class GuideServiceTests {
    @Autowired
    GuideService guideService;
    @Autowired
    KidRepository kidRepository;

    @Test
    public void processPaymentTest(){
        List<Kid> kids = kidRepository.findAll();
        guideService.processPayment(1L, 1L, kids);
    }
}
