package com.app.babybaby.repository.reply.parentsBoardReply;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParentsBoardReplyQueryDsl {
//  전체 댓글 수 가져오기
    public Long parentsBoardReplyCount();

//    댓글 전체 조회
    public Page<ParentsBoardReply> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id);

}
