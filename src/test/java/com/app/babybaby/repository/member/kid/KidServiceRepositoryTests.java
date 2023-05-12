package com.app.babybaby.repository.member.kid;

import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.type.GenderType;
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
public class KidServiceRepositoryTests {
    @Autowired
    KidRepository kidRepository;
    @Autowired
    MemberRepository memberRepository;

    public void findAllKidsByEventId_QueryDslTest(){
    }


    @Test
    public void saveTest(){
        Kid kid = new Kid("김동한", 11, GenderType.MAN,memberRepository.findById(1L).get());
        kidRepository.save(kid);
    }


}
