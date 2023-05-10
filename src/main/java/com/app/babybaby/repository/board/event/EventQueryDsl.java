package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventQueryDsl {
    //    이벤트 게시판 목록
    public Page<Event> findEventList(Pageable pageable);
    //    이벤트 게시판 상세 페이지
    public Optional<Event> findEventById(Long id);
    //     결제 상세 페이지
    public Optional<Event> findEventPayById(Long memberId, Long eventId);

}
