package com.app.babybaby.repository.alert;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.repository.member.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class AlertRepositoryTest {
    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findByMemberIdTest(){
        memberRepository.findById(1L).ifPresent(member -> {
            alertRepository.findAllByMemberId(member.getId()).stream().map(Alert::toString).forEach(log::info);
        });
    }
}