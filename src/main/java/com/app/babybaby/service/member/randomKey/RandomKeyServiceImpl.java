package com.app.babybaby.service.member.randomKey;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.RandomKey;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.member.member.RandomKeyRepository;
import com.app.babybaby.service.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("randomKey") @Primary
@Slf4j
public class RandomKeyServiceImpl implements RandomKeyService {
    private final MemberRepository memberRepository;
    private final RandomKeyRepository randomKeyRepository;

    @Override
    public RandomKey saveRandomKey(Member member) {
        RandomKey randomKey = new RandomKey(member);
//        log.info(member.toString());
//        log.info(randomKey.toString());
        randomKeyRepository.save(randomKey);
        return randomKey;
    }

    @Override
    public RandomKey getLatestRandomKey(Long id) {
        return randomKeyRepository.getLatestRandomKey(id);
    }
}
