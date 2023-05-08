package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.repository.like.nowKids.NowKidsLikeQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NowKidsRepository extends JpaRepository<NowKids, Long>, NowKidsQueryDsl {
}
