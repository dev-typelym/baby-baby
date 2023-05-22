package com.app.babybaby.repository.like.eventLike;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.like.eventLike.EventLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EventLikeQueryDsl {
    //    회원이 좋아요 누른 이벤트 게시글 조회
    public Slice<EventLike> findAllByMemberLikesWithPaging_QueryDSL(Pageable pageable, Long memberId);
//     회원이 좋아요를 누른지 안누른지 확인
    public boolean hasMemberLikedEvent(Long memberId, Long eventId);
}
