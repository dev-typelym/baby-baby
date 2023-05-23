package com.app.babybaby.repository.reply.reviewReply;

import com.app.babybaby.entity.reply.reviewReply.ReviewReply;
import com.app.babybaby.search.admin.AdminReviewReplySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewReplyQueryDsl {

    //    [관리자] 리뷰 댓글 목록
    public Page<ReviewReply> findAlLReviewReplyWithSearch_queryDSL(Pageable pageable, AdminReviewReplySearch adminReviewReplySearch);

    //    [관리자] 리뷰 댓글 삭제
    public void deleteReviewBoardReplyByIds_queryDSL(Long reviewReplyId);
}
