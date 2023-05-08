package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.parentsBoard.QParentsBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class NowKidsQueryDslImpl implements NowKidsQueryDsl {
    @Autowired
    private final JPAQueryFactory query;

    @Override
    public Page<ParentsBoard> findAllWithPaging(Pageable pageable) {
        List<ParentsBoard> foundParentsBoard = query.select(QParentsBoard.parentsBoard)
                .from(QParentsBoard.parentsBoard)
                .orderBy(QParentsBoard.parentsBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(QParentsBoard.parentsBoard.count())
                .from(QParentsBoard.parentsBoard)
                .fetchOne();

        return new PageImpl<>(foundParentsBoard, pageable, count);
    }
}
