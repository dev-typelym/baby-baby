package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.parentsBoard.QParentsBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ParentsBoardQueryDslImpl implements ParentsBoardQueryDsl {
    private final JPAQueryFactory query;

//    @Override
//    public Page<ParentsBoard> findAllWithPaging(Pageable pageable) {
//        List<ParentsBoard> foundParentsBoard = query.select(QParentsBoard.parentsBoard)
//                .from(QParentsBoard.parentsBoard)
//                .orderBy(QParentsBoard.parentsBoard.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        Long count = query.select(QParentsBoard.parentsBoard.count())
//                .from(QParentsBoard.parentsBoard)
//                .fetchOne();
//
//        return new PageImpl<>(foundParentsBoard, pageable, count);
//    }
}
