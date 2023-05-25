package com.app.babybaby.repository.reply.parentsBoardReply;

import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.entity.reply.parentsBoardReply.QParentsBoardReply;
import com.app.babybaby.search.admin.AdminParentsBoardReplySearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;
import static com.app.babybaby.entity.reply.parentsBoardReply.QParentsBoardReply.parentsBoardReply;

@RequiredArgsConstructor
public class ParentsBoardReplyQueryDslImpl implements ParentsBoardReplyQueryDsl {

    private final JPAQueryFactory query;

//    전체 댓글 수 가져오기
    @Override
    public Long parentsBoardReplyCount(Long boardId) {
        Long parentsBoardReplyCount =
                query.select(parentsBoardReply.count())
                        .from(parentsBoardReply)
                        .where(parentsBoardReply.parentsBoard.id.eq(boardId))
                        .fetchOne();

        return parentsBoardReplyCount;
    }

    //    목록 가져오기(페이징)
    @Override
    public Page<ParentsBoardReply> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id) {
        List<ParentsBoardReply> foundParentsBoardReply = query.select(parentsBoardReply)
                .from(parentsBoardReply)
                .join(parentsBoardReply.parentsBoard)
                .join(parentsBoardReply.member)
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


    //    [관리자] 부모님마당 댓글 목록 조회
    @Override
    public Page<ParentsBoardReply> findAlLParentsBoardReplyWithSearch_queryDSL(Pageable pageable , AdminParentsBoardReplySearch adminParentsBoardReplySearch) {
        BooleanExpression parentsBoardContentEq = adminParentsBoardReplySearch.getParentsBoardReplyContent() == null ? null : parentsBoardReply.ParentsBoardReplyContent.like("%" + adminParentsBoardReplySearch.getParentsBoardReplyContent() + "%");

        QParentsBoardReply parentsBoardReply = QParentsBoardReply.parentsBoardReply;

        List<ParentsBoardReply> foundParentsBoardReply = query.select(parentsBoardReply)
                .from(parentsBoardReply)
                .where(parentsBoardContentEq)
                .orderBy(parentsBoardReply.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(parentsBoardReply.count())
                .from(parentsBoardReply)
                .where(parentsBoardContentEq)
                .fetchOne();

        return new PageImpl<>(foundParentsBoardReply, pageable, count);
    }

    //  [관리자] 부모님마당 댓글 목록 삭제
    @Override
    public void deleteParentsBoardReplyByIds_queryDSL(Long parentsBoardReplyId) {
        query.delete(parentsBoardReply)
                .where(parentsBoardReply.id.in(parentsBoardReplyId))
                .execute();
    }

}
