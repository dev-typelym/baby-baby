package com.app.babybaby.service.board.review;

import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.entity.board.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    public Page<ReviewDTO> findReviewById(Long memberId, Pageable pageable);



    default ReviewDTO ReviewToDTO(Review review){
        return ReviewDTO.builder()
                .boardTitle(review.getBoardTitle())
                .id(review.getId())
                .memberId(review.getMember().getId())
                .reviewScore(review.getReviewScore())
                .build();
    }

}
