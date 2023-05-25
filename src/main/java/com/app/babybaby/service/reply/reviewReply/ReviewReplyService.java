package com.app.babybaby.service.reply.reviewReply;

import com.app.babybaby.domain.replyDTO.reviewReplyDTO.ReviewReplyDTO;
import com.app.babybaby.entity.reply.reviewReply.ReviewReply;

public interface ReviewReplyService {

    default ReviewReply toReviewReplyEntity(ReviewReplyDTO reviewReplyDTO){
        return ReviewReply.builder()
                .id(reviewReplyDTO.getId())
                .reviewReplyContent(reviewReplyDTO.getReviewReplyContent())
                .build();
    }
}
