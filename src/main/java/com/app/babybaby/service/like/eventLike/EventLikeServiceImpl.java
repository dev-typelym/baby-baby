package com.app.babybaby.service.like.eventLike;

import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.domain.purchaseDTO.couponDTO.CouponDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.like.eventLike.EventLike;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.like.eventLike.EventLikeRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventLikeServiceImpl implements EventLikeService {
    private final EventLikeRepository eventLikeRepository;

    private final EventRepository eventRepository;

    private final MemberRepository memberRepository;

    @Override
    public Slice<EventLikeDTO> findEventLikeByMemberId(Pageable pageable, Long memberId) {
        Slice<EventLike> eventLikes = eventLikeRepository.findAllByMemberLikesWithPaging_QueryDSL(pageable, memberId);
        List<EventLikeDTO> collect = eventLikes.get().map(eventLike -> EventToDTO(eventLike)).collect(Collectors.toList());

        return new SliceImpl<>(collect, pageable, eventLikes.hasNext());
    }

    @Override
    public void likeSave(Long nowKidsId, Long memberId) {
        Event event = eventRepository.findById(nowKidsId).get();
        Member member = memberRepository.findById(memberId).get();
        EventLike eventLike = new EventLike(event,member);
        eventLikeRepository.save(eventLike);
    }

    @Override @Transactional
    public void deleteLike(Long eventId, Long memberId) {
        eventLikeRepository.deleteByEventIdAndMemberId(eventId, memberId);
    }

}
