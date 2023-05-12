package com.app.babybaby.repository.reply.reviewReply;

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

import static com.app.babybaby.entity.reply.reviewReply.QReviewReply.reviewReply;

@RequiredArgsConstructor
public class ReviewReplyQueryDslImpl implements ReviewReplyQueryDsl {

    private final JPAQueryFactory query;

    //    [관리자] 리뷰 댓글 목록 조회
    @Override
    public Page<ReviewReply> findAlLReviewReplyWithSearch_queryDSL(Pageable pageable, AdminReviewReplySearch adminReviewReplySearch) {
             BooleanExpression reviewReplyContentEq = adminReviewReplySearch.getReviewReplyContent() == null ? null : reviewReply.ReviewReplyContent.eq(adminReviewReplySearch.getReviewReplyContent());

        QReviewReply reviewReply = QReviewReply.reviewReply;

        List<ReviewReply> foundReviewReply = query.select(reviewReply)
                .from(reviewReply)
                .where(reviewReplyContentEq)
                .orderBy(reviewReply.id.asc())
                .offset(pageable.getOffset() - 1)
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
    public void deleteReviewBoardReplyByIds_queryDSL(List<Long> reviewReplyIds) {
        query.delete(reviewReply)
                .where(reviewReply.id.in(reviewReplyIds))
                .execute();
    }
}
