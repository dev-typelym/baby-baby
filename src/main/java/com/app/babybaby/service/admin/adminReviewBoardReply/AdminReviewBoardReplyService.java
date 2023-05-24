package com.app.babybaby.service.admin.adminReviewBoardReply;

import com.app.babybaby.domain.adminDTO.AdminReviewReplyDTO;
import com.app.babybaby.entity.reply.reviewReply.ReviewReply;
import com.app.babybaby.search.admin.AdminReviewReplySearch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminReviewBoardReplyService {

    //    관리자 리뷰 댓글 목록
    public Page<AdminReviewReplyDTO> getAdminReviewReplyListWithPaging(int page, AdminReviewReplySearch adminReviewReplySearch);

    //    관리자 리뷰 댓글 삭제하기
    public void deleteAdminReviewReply(List<String> replyIds);

    default AdminReviewReplyDTO toAdminReviewReplyDTO(ReviewReply reviewReply){
        return AdminReviewReplyDTO.builder()
                .id(reviewReply.getId())
                .reviewTitle(reviewReply.getReview().getBoardTitle())
                .replyContent(reviewReply.getReviewReplyContent())
                .writeDate(reviewReply.getRegisterDate())
                .build();
    }
}
