package com.app.babybaby.repository.like;

import com.app.babybaby.entity.like.EventLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLikeRepository extends JpaRepository<EventLike, Long>, EventLikeQueryDsl {
}
