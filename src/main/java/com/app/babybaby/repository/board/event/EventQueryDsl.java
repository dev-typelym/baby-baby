package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface EventQueryDsl {
    //    이벤트 게시판 목록
    public Page<Event> findEventList(Pageable pageable);
    //    이벤트 게시판 상세 페이지
    public Optional<Event> findEventById(Long id);
    //     결제 상세 페이지
    public Optional<Event> findEventPayById(Long memberId, Long eventId);
//    회원이 좋아요 누른 이벤트 게시글 조회
    public Slice<Event> findAllByMemberLikesWithPaging_QueryDSL(Pageable pageable, Long memberId);
}
