package com.app.babybaby.repository.reply.parentsBoardReply;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentsBoardRepository extends JpaRepository<ParentsBoard,Long>, ParentsBoardQueryDsl {
}
