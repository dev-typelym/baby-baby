package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParentsBoardQueryDsl {
//    전체 목록
    public Page<ParentsBoard> findAllWithPaging(Pageable pageable);
}
