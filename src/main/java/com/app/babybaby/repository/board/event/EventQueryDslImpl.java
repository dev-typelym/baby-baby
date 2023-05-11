package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.like.eventLike.QEventLike;
import com.app.babybaby.entity.purchase.coupon.QCoupon;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.like.eventLike.QEventLike.eventLike;
import static com.app.babybaby.entity.purchase.coupon.QCoupon.coupon;

@RequiredArgsConstructor
public class EventQueryDslImpl implements EventQueryDsl {
    private final JPAQueryFactory query;


    //    이벤트 게시판 목록
    //    이벤트 게시판 검색 페이징
    @Override
    public Slice<Event> findEventList(EventBoardSearch eventBoardSearch,Pageable pageable) {

        BooleanExpression eventTitleContains = eventBoardSearch.getBoardTitle() == null ? null : event.boardTitle.contains(eventBoardSearch.getBoardTitle());
        BooleanExpression eventContentContains = eventBoardSearch.getBoardContent() == null ? null : event.boardContent.contains(eventBoardSearch.getBoardContent());
        BooleanExpression eventCategoryContains = eventBoardSearch.getCategoryType() == null ? null : event.category.eq(eventBoardSearch.getCategoryType());

        List<Event> events = query.select(event)
                .from(event)
                .join(event.company).fetchJoin()
                .leftJoin(event.eventFiles).fetchJoin()
                .where(eventTitleContains, eventContentContains, eventCategoryContains)
                .orderBy(event.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = false;
        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (events.size() > pageable.getPageSize()) {
            hasNext = true;
            events.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(events, pageable, hasNext);
    }



    //    일단 이벤트 게시판 상세
    @Override
    public Optional<Event> findEventById(Long eventId) {
        return Optional.ofNullable(
                query.select(event)
                        .from(event)
                        .join(event.company)
                        .fetchJoin()
                        .where(event.id.eq(eventId))
                        .fetchOne());
    }


    //    결제 상세페이지
    public Optional<Event> findEventPayById(Long memberId, Long eventId) {
        return Optional.ofNullable(
                query.select(event)
                        .from(event)
                        .join(event.company)
                        .fetchJoin()
                        .join(coupon)
                        .fetchJoin()
                        .on(coupon.member.id.eq(memberId))
                        .where(event.id.eq(eventId), event.company.id.eq(memberId))
                        .fetchOne()
        );
    }


    



}
