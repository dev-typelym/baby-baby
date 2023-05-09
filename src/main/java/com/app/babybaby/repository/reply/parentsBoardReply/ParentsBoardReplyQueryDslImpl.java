package com.app.babybaby.repository.reply.parentsBoardReply;

import com.app.babybaby.entity.reply.parentsBoardReply.QParentsBoardReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.babybaby.entity.reply.parentsBoardReply.QParentsBoardReply.parentsBoardReply;

@RequiredArgsConstructor
public class ParentsBoardReplyQueryDslImpl implements ParentsBoardReplyQueryDsl {

    private final JPAQueryFactory query;

    @Override
    public Long parentsBoardReplyCount() {
        Long parentsBoardReplyCount =
                query.select(parentsBoardReply.count())
                        .from(parentsBoardReply)
                        .fetchOne();

        return parentsBoardReplyCount;
    }
}
