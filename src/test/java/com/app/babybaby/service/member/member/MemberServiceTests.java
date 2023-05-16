package com.app.babybaby.service.member.member;

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
public class MemberServiceTests {
    @Autowired
    MemberService memberService;

    @Test
    public void getAllMemberInfoTest() {
        log.info(memberService.getAllMemberInfo(1L).toString());
    }
}
