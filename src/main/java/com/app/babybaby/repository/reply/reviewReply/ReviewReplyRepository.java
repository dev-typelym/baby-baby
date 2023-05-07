package com.app.babybaby.repository.reply.reviewReply;

import com.app.babybaby.entity.reply.ReviewReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long>, ReviewReplyQueryDsl {
}
