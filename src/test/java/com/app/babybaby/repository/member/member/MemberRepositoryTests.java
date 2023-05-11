package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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

    //    일반 회원가입
//    회원 20명 저장
    @Test
    public void save20MemberTest() {
        Address address = new Address();
        address.setAddress("분당");
        address.setAddressDetail("d");
        address.setAddressSubDetail("dd");
        address.setPostcode("038316");

        for (int i = 0; i < 20; i++) {
            String uniqueNickname = "Nickname" + i;

            Member member = new Member(
                    "you" + i + "@gmail.com",
                    "이름" + i,
                    "1234",
                    uniqueNickname,
                    "안녕하세요",
                    "0101234123" + i,
                    address,
                    "defaultImage.png",
                    "defaultImageUUID",
                    "/defaultImage",
                    LocalDateTime.now(),
                    null,
                    null,
                    SleepType.AWAKE,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            memberRepository.save(member);
        }

    }



}
