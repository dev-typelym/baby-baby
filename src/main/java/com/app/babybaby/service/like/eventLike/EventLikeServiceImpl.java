package com.app.babybaby.service.like.eventLike;

import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.domain.purchaseDTO.couponDTO.CouponDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.like.eventLike.EventLike;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.repository.like.eventLike.EventLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventLikeServiceImpl implements EventLikeService {
    private final EventLikeRepository eventLikeRepository;

    @Override
    public Slice<EventLikeDTO> findEventLikeByMemberId(Pageable pageable, Long memberId) {
        Slice<Event> eventLikes = eventLikeRepository.findAllByMemberLikesWithPaging_QueryDSL(pageable, memberId);
        List<EventLikeDTO> collect = eventLikes.get().map(event -> EventToDTO(event)).collect(Collectors.toList());

        return new SliceImpl<>(collect, pageable, eventLikes.hasNext());
    }

}
