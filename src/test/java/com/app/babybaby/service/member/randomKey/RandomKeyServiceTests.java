package com.app.babybaby.service.member.randomKey;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.RandomKey;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.member.member.RandomKeyRepository;
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
public class RandomKeyServiceTests {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RandomKeyRepository randomKeyRepository;


    @Test
    public void randomKeyTest(){
        Member member = memberRepository.findByMemberEmail("frencine0624@gmail.com").get();
        RandomKey randomKey = new RandomKey(member);
        log.info(member.toString());
        log.info(randomKey.toString());

        randomKeyRepository.save(randomKey);

        log.info(memberRepository.findMemberByRandomKey(randomKey.getRandomKey()).toString());

    }

}
