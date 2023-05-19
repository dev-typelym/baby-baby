package com.app.babybaby.service.like.nowKidsLike;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.domain.likeDTO.nowKidsLikeDTO.NowKidsLikeDTO;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Slice<NowKidsLikeDTO> findEventLikeByMemberId(Pageable pageable, Long memberId) {
        Slice<NowKids> nowKidsLikes = nowKidsLikeRepository.findAllByMemberLikesWithPaging_QueryDSL(pageable, memberId);
        List<NowKidsLikeDTO> collect = nowKidsLikes.get().map(nowKids -> toNowKidsLikeDTO(nowKids)).collect(Collectors.toList());

        return new SliceImpl<>(collect, pageable, nowKidsLikes.hasNext());
    }


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
