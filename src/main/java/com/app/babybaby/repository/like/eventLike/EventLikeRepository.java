package com.app.babybaby.repository.like.eventLike;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.like.eventLike.EventLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLikeRepository extends JpaRepository<EventLike, Long>, EventLikeQueryDsl {
    public void deleteByEventIdAndMemberId(Long eventId,Long memberId);
}
