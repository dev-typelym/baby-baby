package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewQueryDsl {
    public Page<Review> findAllByMemberId(Long memberId, Pageable pageable);
}
