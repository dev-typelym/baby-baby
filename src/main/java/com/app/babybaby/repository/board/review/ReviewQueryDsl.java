package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.review.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReviewQueryDsl {
    public List<Review> findReviewById(Long memberId);
    public Slice<Review> findAllByMemberId(Pageable pageable, Long memberId);
}
