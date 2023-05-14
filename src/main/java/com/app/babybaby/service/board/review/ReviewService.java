package com.app.babybaby.service.board.review;

import com.app.babybaby.entity.board.review.Review;

import java.util.List;

public interface ReviewService {
    public List<Review> findReviewById(Long memberId);

}
