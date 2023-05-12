package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.event.QEvent;
import com.app.babybaby.entity.board.review.QReview;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.search.admin.AdminReviewSearch;
import com.app.babybaby.type.CategoryType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;

import static com.app.babybaby.entity.board.review.QReview.review;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.review.QReview.review;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory query;

//    나의리뷰 조회
    @Override
    public List<Review> findReviewById_QueryDSL(Long memberId) {
        return query.select(review)
                .from(review)
                .join(review.member).fetchJoin()
                .where(review.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public Slice<Review> findAllByMemberId_QueryDSL(Pageable pageable, Long memberId) {
        List<Review> reviewList = query.select(review)
                .from(review)
                .where(review.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (reviewList.size() > pageable.getPageSize()) {
            hasNext = true;
            reviewList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(reviewList, pageable, hasNext);
    }

    //  [관리자] 리뷰 전체 목록 조회
    @Override
    public Page<Review> findAllReviewBoardWithSearch_queryDSL(Pageable pageable , AdminReviewSearch adminReviewSearch, CategoryType eventCategory) {
        BooleanExpression reviewContentEq = adminReviewSearch.getReviewContent() == null ? null : review.boardContent.eq(adminReviewSearch.getReviewContent());

        QReview review = QReview.review;
        QEvent event = QEvent.event;

        List<Review> foundReviewBoard = query.select(review)
                .from(review)
                .join(review.event)
                .fetchJoin()
                .join(review.member)
                .fetchJoin()
                .where((eventCategory != null ? review.event.category.eq(eventCategory) : review.event.category.isNotNull()).and(reviewContentEq))
                .orderBy(review.id.asc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(review.count())
                .from(review)
                .where((eventCategory != null ? review.event.category.eq(eventCategory) : review.event.category.isNotNull()).and(reviewContentEq))
                .fetchOne();

        return new PageImpl<>(foundReviewBoard, pageable, count);
    }

    //  [관리자] 리뷰 상세보기
    @Override
    public Optional<Review> findReviewBoardById_queryDSL(Long reviewBoardId) {
        return Optional.ofNullable(
                query.select(review)
                        .from(review)
                        .join(review.event)
                        .fetchJoin()
                        .leftJoin(review.reviewFiles)
                        .fetchJoin()
                        .join(review.member)
                        .fetchJoin()
                        .where(review.id.eq(reviewBoardId))
                        .fetchOne()
        );
    }

    // [관리자] 리뷰 삭제하기
    @Override
    public void deleteReviewBoardByIds_queryDSL(List<Long> reviewBoardIds) {
        query.delete(review)
                .where(review.id.in(reviewBoardIds))
                .execute();
    }
}
