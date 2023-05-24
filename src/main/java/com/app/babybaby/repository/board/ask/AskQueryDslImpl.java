package com.app.babybaby.repository.board.ask;

import com.app.babybaby.entity.board.ask.Ask;
import com.app.babybaby.entity.board.ask.QAsk;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.search.admin.AdminAskSearch;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.type.ProcessType;
import com.app.babybaby.type.SleepType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.ask.QAsk.ask;
import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.board.nowKids.QNowKids.nowKids;

@RequiredArgsConstructor
public class AskQueryDslImpl implements AskQueryDsl {


    private final JPAQueryFactory query;

    //    내가쓴 문의 목록
    @Override
    public Slice<Ask> findAllAskByMemberId(Long memberId, Pageable pageable, AdminAskSearch adminAskSearch) {
        BooleanExpression askTitleContains = adminAskSearch.getAskTitle() == null ? null : ask.boardTitle.contains(adminAskSearch.getAskTitle());

        List<Ask> asks = query.select(ask)
                .from(ask)
                .leftJoin(ask.askAnswer).fetchJoin()
                .where(ask.member.id.eq(memberId),askTitleContains)
                .orderBy(ask.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = false;
        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (asks.size() > pageable.getPageSize()) {
            hasNext = true;
            asks.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(asks, pageable, hasNext);
    }


    //  [관리자] 문의 목록 조회
    @Override
    public Page<Ask> findAllAsk_queryDSL(Pageable pageable, AdminAskSearch adminAskSearch, String askStatus) {
        BooleanExpression askTitleEq = adminAskSearch.getAskTitle() == null ? null : ask.boardTitle.like("%" +adminAskSearch.getAskTitle()+ "%");
        QAsk ask = QAsk.ask;
        List<Ask> foundAsk = query.select(ask)
                .from(ask)
                .where(
                        askTitleEq.and(
                                askStatus.equals("전체")
                                        ? ask.askStatus.isNotNull()
                                        : askStatus.equals("대기답변")
                                        ? ask.askStatus.eq(ProcessType.HOLD)
                                        : ask.askStatus.isNotNull()
                        )
                )
                .orderBy(ask.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(ask.count())
                .from(ask)
                .where(
                        askTitleEq.and(
                                askStatus.equals("전체")
                                        ? ask.askStatus.isNotNull()
                                        : askStatus.equals("대기답변")
                                        ? ask.askStatus.eq(ProcessType.HOLD)
                                        : ask.askStatus.isNotNull()
                        )
                )
                .fetchOne();

        return new PageImpl<>(foundAsk, pageable, count);
    }

    @Override
    //  [관리자] 문의 상세보기
    public List<Ask> findAllAskDetail_queryDSL() {
        List<Ask> foundAsktDetail = query.select(ask)
                .from(ask)
                .fetch();
        return foundAsktDetail;
    }



    //  [관리자] 문의 삭제하기
    @Override
    public void deleteAskByIds_queryDSL(Long askId) {
        query.delete(ask)
                .where(ask.id.in(askId))
                .execute();
    }

    //  [관리자] 문의 아이디로 찾기
    @Override
    public Ask findAskById_queryDSL(Long askId) {
        return query.select(ask)
                .from(ask)
                .where(ask.id.eq(askId))
                .fetchOne();

    }

    //  [관리자] 문의 답변시 상태 바꾸기
    @Override
    public void changeAskStatusById_queryDSL(Long askId) {
        query.update(ask)
                .set(ask.askStatus, ProcessType.END)
                .where(ask.id.eq(askId))
                .execute();
    }
}
