package com.app.babybaby.repository.like;

import com.app.babybaby.entity.like.NowKidsLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NowKidsLikeRepository extends JpaRepository<NowKidsLike, Long>, NowKidsLikeQueryDsl {
}
