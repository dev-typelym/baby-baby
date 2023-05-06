package com.app.babybaby.repository.like.nowKids;

import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NowKidsLikeRepository extends JpaRepository<NowKidsLike, Long>, NowKidsLikeQueryDsl {
}
