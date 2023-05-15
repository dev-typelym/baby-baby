package com.app.babybaby.service.like.nowKidsLike;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.like.nowKidsLike.NowKidsLikeRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("nowKidsLike")
@Primary
@Slf4j
public class NowKidsLikeServiceImpl implements NowKidsLikeService {

    private final NowKidsLikeRepository nowKidsLikeRepository;

    private final NowKidsRepository nowKidsRepository;

    private final MemberRepository memberRepository;

    private final EventRepository eventRepository;

    @Override
    public void likeSave(Long nowKidsId, Long memberId) {
        NowKids nowKids = nowKidsRepository.findById(nowKidsId).get();
        Member member = memberRepository.findById(memberId).get();
        NowKidsLike nowKidsLike = new NowKidsLike(nowKids,member);
        nowKidsLikeRepository.save(nowKidsLike);
    }

    @Override @Transactional
    public void deleteLike(Long nowKidsId, Long memberId) {
        nowKidsLikeRepository.deleteByNowKidsIdAndMemberId(nowKidsId, memberId);
    }
}
