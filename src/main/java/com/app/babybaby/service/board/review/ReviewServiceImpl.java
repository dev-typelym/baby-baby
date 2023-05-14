package com.app.babybaby.service.board.review;

import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> findReviewById(Long memberId) {
        return reviewRepository.findReviewById_QueryDSL(memberId);
    }
}
