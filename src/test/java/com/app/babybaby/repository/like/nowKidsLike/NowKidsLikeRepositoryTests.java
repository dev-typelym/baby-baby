package com.app.babybaby.repository.like.nowKidsLike;

import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
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
public class NowKidsLikeRepositoryTests {
    @Autowired
    NowKidsLikeRepository nowKidsLikeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    NowKidsRepository nowKidsRepository;

    @Test
    public void saveTest(){
        Member member = memberRepository.findById(1L).get();
        NowKids nowKids =nowKidsRepository.findById(4L).get();
        NowKidsLike nowKidsLike = new NowKidsLike(nowKids, member);
        nowKidsLikeRepository.save(nowKidsLike);
    }

    @Test
    public void hasMemberLikedNowKidsTest(){
        log.info(String.valueOf(nowKidsLikeRepository.hasMemberLikedNowKids(1L, 42L)));
    }

}
