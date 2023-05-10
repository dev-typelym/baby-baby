package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.alert.Alert;
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
public class MemberRepositoryTests {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void findAlertTest() {
        memberRepository.findById(1L).ifPresent(member -> {
            log.info(member.toString());
        });
    }
}
