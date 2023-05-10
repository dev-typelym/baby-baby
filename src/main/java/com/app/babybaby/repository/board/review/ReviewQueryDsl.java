package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.review.Review;

import java.util.List;

public interface ReviewQueryDsl {
    public List<Review> findReviewById(Long memberId);
}
