package com.app.babybaby.repository.like.nowKidsLike;

import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NowKidsLikeRepository extends JpaRepository<NowKidsLike, Long>, NowKidsLikeQueryDsl {
}
