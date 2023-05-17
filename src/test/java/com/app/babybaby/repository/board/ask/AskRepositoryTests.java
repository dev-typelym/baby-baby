package com.app.babybaby.repository.board.ask;

import com.app.babybaby.entity.board.ask.Ask;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.type.ProcessType;
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
public class AskRepositoryTests {

    @Autowired
    AskRepository askRepository;
    @Autowired
    MemberRepository memberRepository;


    @Test
    public void saveTest(){

        for (int i = 0; i<10; i++){
            Ask ask = new Ask("문의 합니다 의택님" + i,
                    "왜이렇게 잘하세요 의택님 부러워요" + i,
                    ProcessType.HOLD,
                    memberRepository.findById(1L).get());

            askRepository.save(ask);
        }

    }



}
