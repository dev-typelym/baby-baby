package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentsBoardRepository extends JpaRepository<ParentsBoard, Long>, ParentsBoardQueryDsl {
    public Page<ParentsBoard> findAllByMemberId(Long memberId, Pageable pageable);

    public Long countBy();
}
