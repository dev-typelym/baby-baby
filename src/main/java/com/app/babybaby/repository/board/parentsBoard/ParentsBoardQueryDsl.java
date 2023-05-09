package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import org.hibernate.annotations.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ParentsBoardQueryDsl {
//    전체 목록
    public Page<ParentsBoard> findAllWithSearch(Pageable pageable);

//    상세보기
    public Optional<ParentsBoard> findById(Long id);

}
