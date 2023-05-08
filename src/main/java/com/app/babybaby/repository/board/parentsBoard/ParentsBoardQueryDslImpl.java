package com.app.babybaby.repository.board.parentsBoard;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ParentsBoardQueryDslImpl implements ParentsBoardQueryDsl {
    private final JPAQueryFactory query;


}
