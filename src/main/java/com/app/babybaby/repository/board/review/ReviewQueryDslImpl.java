package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.review.QReview;
import com.app.babybaby.entity.board.review.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.babybaby.entity.board.review.QReview.review;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory query;

//    나의리뷰 조회
    @Override
    public List<Review> findReviewById(Long memberId) {
        return query.select(review)
                .from(review)
                .join(review.member).fetchJoin()
                .where(review.member.id.eq(memberId))
                .fetch();
    }

}
