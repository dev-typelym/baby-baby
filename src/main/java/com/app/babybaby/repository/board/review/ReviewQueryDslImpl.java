package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.review.QReview;
import com.app.babybaby.entity.board.review.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.babybaby.entity.board.review.QReview.review;

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

    @Override
    public Slice<Review> findAllByMemberId(Pageable pageable, Long memberId) {
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
}
