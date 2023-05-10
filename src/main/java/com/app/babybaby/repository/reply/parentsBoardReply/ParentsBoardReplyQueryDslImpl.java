package com.app.babybaby.repository.reply.parentsBoardReply;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;
import static com.app.babybaby.entity.reply.parentsBoardReply.QParentsBoardReply.parentsBoardReply;

@RequiredArgsConstructor
public class ParentsBoardReplyQueryDslImpl implements ParentsBoardReplyQueryDsl {

    private final JPAQueryFactory query;

//    전체 댓글 수 가져오기
    @Override
    public Long parentsBoardReplyCount() {
        Long parentsBoardReplyCount =
                query.select(parentsBoardReply.count())
                        .from(parentsBoardReply)
                        .fetchOne();

        return parentsBoardReplyCount;
    }

    //    목록 가져오기(페이징)
    @Override
    public Page<ParentsBoardReply> findAllByBoardIdWithPaging(Long id, Pageable pageable) {
        List<ParentsBoardReply> foundParentsBoardReply = query.select(parentsBoardReply)
                .from(parentsBoardReply)
                .join(parentsBoardReply.parentsBoard)
                .fetchJoin()
                .leftJoin(parentsBoardReply.member)
                .fetchJoin()
                .orderBy(parentsBoardReply.id.desc())
                .where(parentsBoardReply.parentsBoard.id.eq(id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(parentsBoardReply.count())
                .from(parentsBoardReply)
                .fetchOne();

        return new PageImpl<>(foundParentsBoardReply, pageable, count);
    }

}
