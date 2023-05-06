package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewQueryDsl {
}
