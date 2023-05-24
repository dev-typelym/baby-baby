package com.app.babybaby.service.admin.adminReviewBoardReply;

import com.app.babybaby.domain.adminDTO.AdminReviewReplyDTO;
import com.app.babybaby.entity.reply.reviewReply.ReviewReply;
import com.app.babybaby.repository.reply.reviewReply.ReviewReplyRepository;
import com.app.babybaby.search.admin.AdminReviewReplySearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminReviewBoardReplyServiceImpl implements AdminReviewBoardReplyService {

    @Autowired
    private ReviewReplyRepository reviewReplyRepository;

//    관리자 리뷰 댓글 목록
    @Override
    public Page<AdminReviewReplyDTO> getAdminReviewReplyListWithPaging(int page, AdminReviewReplySearch adminReviewReplySearch) {
        Page<ReviewReply> reviewReplies = reviewReplyRepository.findAlLReviewReplyWithSearch_queryDSL(PageRequest.of(page, 5), adminReviewReplySearch);
        List<AdminReviewReplyDTO> adminParentsBoardDTOS = reviewReplies.getContent().stream()
                .map(this::toAdminReviewReplyDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(adminParentsBoardDTOS, reviewReplies.getPageable(), reviewReplies.getTotalElements());
    }

//    관리자 리뷰 댓글 삭제
    @Override
    public void deleteAdminReviewReply(List<String> replyIds) {
        replyIds.stream().map(replyId -> Long.parseLong(replyId)).forEach(reviewReplyRepository::deleteReviewBoardReplyByIds_queryDSL);
    }
}
