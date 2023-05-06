package com.app.babybaby.repository.board;

import com.app.babybaby.entity.board.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewQueryDsl {
}
