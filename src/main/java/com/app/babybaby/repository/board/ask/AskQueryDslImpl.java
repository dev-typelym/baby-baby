package com.app.babybaby.repository.board.ask;

import com.app.babybaby.entity.board.ask.Ask;
import com.app.babybaby.entity.board.ask.QAsk;
import com.app.babybaby.search.admin.AdminAskSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.ask.QAsk.ask;

@RequiredArgsConstructor
public class AskQueryDslImpl implements AskQueryDsl {

    private final JPAQueryFactory query;

    //  [관리자] 문의 목록 조회
    @Override
    public Page<Ask> findAllAsk_queryDSL(Pageable pageable, AdminAskSearch adminAskSearch) {
        BooleanExpression askTitleEq = adminAskSearch.getAskTitle() == null ? null : ask.boardTitle.eq(adminAskSearch.getAskTitle());

        QAsk ask = QAsk.ask;
        List<Ask> foundAsk = query.select(ask)
                .from(ask)
                .where(askTitleEq)
                .orderBy(ask.id.asc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(ask.count())
                .from(ask)
                .where(askTitleEq)
                .fetchOne();

        return new PageImpl<>(foundAsk, pageable, count);
    }

    //  [관리자] 문의 상세보기
    @Override
    public Optional<Ask> findAskById_queryDSL(Long askId) {
        return Optional.ofNullable(
                query.select(ask)
                        .from(ask)
                        .where(ask.id.eq(askId))
                        .fetchOne());
    }

    //  [관리자] 문의 삭제하기
    @Override
    public void deleteAskByIds_queryDSL(List<Long> askIds) {
        query.delete(ask)
                .where(ask.id.in(askIds))
                .execute();
    }

    
//    내가쓴 문의 목록
    @Override
    public Slice<Ask> findAllAskByMemberId(Long memberId, Pageable pageable, AdminAskSearch AdminAskSearch) {
        return null;
    }
}
