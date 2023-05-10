package com.app.babybaby.repository.reply.parentsBoardReply;

import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentsBoardReplyRepository extends JpaRepository<ParentsBoardReply,Long>, ParentsBoardReplyQueryDsl {
}
