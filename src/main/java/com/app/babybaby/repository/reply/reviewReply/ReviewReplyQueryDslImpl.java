package com.app.babybaby.repository.reply.reviewReply;

import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.entity.reply.reviewReply.QReviewReply;
import com.app.babybaby.entity.reply.reviewReply.ReviewReply;
import com.app.babybaby.search.admin.AdminReviewReplySearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.babybaby.entity.reply.parentsBoardReply.QParentsBoardReply.parentsBoardReply;
import static com.app.babybaby.entity.reply.reviewReply.QReviewReply.reviewReply;

@RequiredArgsConstructor
public class ReviewReplyQueryDslImpl implements ReviewReplyQueryDsl {

    private final JPAQueryFactory query;

    //    [관리자] 리뷰 댓글 목록 조회
    @Override
    public Page<ReviewReply> findAlLReviewReplyWithSearch_queryDSL(Pageable pageable, AdminReviewReplySearch adminReviewReplySearch) {
        BooleanExpression reviewReplyContentEq = adminReviewReplySearch.getReviewReplyContent() == null ? null : reviewReply.ReviewReplyContent.like("%" + adminReviewReplySearch.getReviewReplyContent() + "%");

        QReviewReply reviewReply = QReviewReply.reviewReply;

        List<ReviewReply> foundReviewReply = query.select(reviewReply)
                .from(reviewReply)
                .where(reviewReplyContentEq)
                .orderBy(reviewReply.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(reviewReply.count())
                .from(reviewReply)
                .where(reviewReplyContentEq)
                .fetchOne();

        return new PageImpl<>(foundReviewReply, pageable, count);
    }

    //  [관리자] 리뷰 댓글 삭제
    @Override
    public void deleteReviewBoardReplyByIds_queryDSL(Long reviewReplyId) {
        query.delete(reviewReply)
                .where(reviewReply.id.in(reviewReplyId))
                .execute();
    }


    //--- 리뷰
//    전체 댓글 수 가져오기
    @Override
    public Long reviewReplyCount(Long reviewId) {
        Long parentsBoardReplyCount =
                query.select(reviewReply.count())
                        .from(reviewReply)
                        .where(reviewReply.review.event.id.eq(reviewId))
                        .fetchOne();

        return parentsBoardReplyCount;
    }

    //    목록 가져오기(페이징)
    @Override
    public Page<ReviewReply> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id) {
        List<ReviewReply> foundReviewReply = query.select(reviewReply)
                .from(reviewReply)
                .join(reviewReply.review.event)
                .fetchJoin()
                .leftJoin(reviewReply.member)
                .fetchJoin()
                .orderBy(reviewReply.id.desc())
                .where(reviewReply.review.event.id.eq(id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(reviewReply.count())
                .from(reviewReply)
                .fetchOne();

        return new PageImpl<>(foundReviewReply, pageable, count);
    }

}
