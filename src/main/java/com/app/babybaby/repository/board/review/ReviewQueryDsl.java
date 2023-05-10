package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.review.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReviewQueryDsl {
    public Slice<Review> findAllByMemberId(Pageable pageable, Long memberId);
}
