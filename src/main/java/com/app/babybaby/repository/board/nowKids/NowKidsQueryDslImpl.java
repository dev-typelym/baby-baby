package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.board.nowKids.QNowKids;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.parentsBoard.QParentsBoard;
import com.app.babybaby.entity.user.Kid;
import com.app.babybaby.entity.user.QKid;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.babybaby.entity.board.nowKids.QNowKids.nowKids;

@RequiredArgsConstructor
public class NowKidsQueryDslImpl implements NowKidsQueryDsl {
    private final JPAQueryFactory query;

    public List<NowKids> findNowKidsEventByDate(){
        return query
                .select(nowKids)
                .from(nowKids)
                .fetch();
    }
}
