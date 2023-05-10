package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.nowKids.NowKids;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NowKidsRepository extends JpaRepository<NowKids, Long>, NowKidsQueryDsl {
}
