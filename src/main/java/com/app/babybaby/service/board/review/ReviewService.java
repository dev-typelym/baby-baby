package com.app.babybaby.service.board.review;

import com.app.babybaby.entity.board.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    public Page<Review> findReviewById(Long memberId, Pageable pageable);

}
