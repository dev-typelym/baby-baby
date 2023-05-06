package com.app.babybaby.repository.board;

import com.app.babybaby.entity.board.NowKids;
import com.app.babybaby.repository.like.NowKidsLikeQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NowKidsRepository extends JpaRepository<NowKids, Long>, NowKidsLikeQueryDsl {
}
