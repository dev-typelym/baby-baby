package com.app.babybaby.repository.reply.parentsBoardReply;

import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.search.admin.AdminParentsBoardReplySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ParentsBoardReplyQueryDsl {
//  전체 댓글 수 가져오기
    public Long parentsBoardReplyCount();

//    댓글 전체 조회
    public Page<ParentsBoardReply> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id);


    //    [관리자] 부모님마당 댓글 목록
    public Page<ParentsBoardReply> findAlLParentsBoardReplyWithSearch_queryDSL(Pageable pageable, AdminParentsBoardReplySearch adminParentsBoardReplySearch);

    //    [관리자] 부모님마당 댓글 삭제
    public void deleteParentsBoardReplyByIds_queryDSL(Long parentsBoardReplyId);

}
