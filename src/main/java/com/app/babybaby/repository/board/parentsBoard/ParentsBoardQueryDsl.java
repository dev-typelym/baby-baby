package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ParentsBoardQueryDsl {
    //    전체 목록
    public Page<ParentsBoard> findAllWithSearch(ParentsBoardSearch parentsBoardSearch, Pageable pageable);

    //    상세보기
    public Optional<ParentsBoard> findById(Long id);

    //    작성하기(1단계)
    public Optional<Event> findByEventId(Long id);


//    작성하기(2단계 대표사진)
}
