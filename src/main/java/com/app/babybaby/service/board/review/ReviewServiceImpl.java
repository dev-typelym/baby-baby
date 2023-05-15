package com.app.babybaby.service.board.review;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Page<ReviewDTO> findReviewById(Long memberId, Pageable pageable) {

        Page<Review> reviews = reviewRepository.findReviewById_QueryDSL(pageable,memberId);
        List<ReviewDTO> reviewDTOS = reviews.stream().map(review -> ReviewToDTO(review)).collect(Collectors.toList());
        log.info(reviewDTOS.toString()+"서비스");
        log.info(reviews.toString()+"리뷰스");
        return new PageImpl<>(reviewDTOS,pageable,reviews.getTotalElements());
    }


}
