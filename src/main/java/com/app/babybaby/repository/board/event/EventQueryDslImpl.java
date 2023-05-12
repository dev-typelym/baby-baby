package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.like.eventLike.QEventLike;
import com.app.babybaby.entity.purchase.coupon.QCoupon;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import com.app.babybaby.entity.purchase.purchase.QPurchase;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;
import static com.app.babybaby.entity.like.eventLike.QEventLike.eventLike;
import static com.app.babybaby.entity.purchase.coupon.QCoupon.coupon;
import static com.app.babybaby.entity.purchase.purchase.QPurchase.purchase;

@RequiredArgsConstructor
public class EventQueryDslImpl implements EventQueryDsl {
    private final JPAQueryFactory query;


    //    이벤트 게시판 목록
    //    이벤트 게시판 검색 페이징
    @Override
    public Slice<Event> findEventListWithPaging_QueryDSL(EventBoardSearch eventBoardSearch,Pageable pageable) {

//        BooleanExpression eventTitleContains = eventBoardSearch.getBoardTitle() == null ? null : event.boardTitle.contains(eventBoardSearch.getBoardTitle());
//        BooleanExpression eventContentContains = eventBoardSearch.getBoardContent() == null ? null : event.boardContent.contains(eventBoardSearch.getBoardContent());
//        BooleanExpression eventCategoryContains = eventBoardSearch.getCategoryType() == null ? null : event.category.eq(eventBoardSearch.getCategoryType());

        List<Event> events = query.select(event)
                .from(event)
                .join(event.company).fetchJoin()
                .leftJoin(event.eventFiles).fetchJoin()
//                .where(eventTitleContains, eventContentContains, eventCategoryContains)
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
    public Optional<Event> findEventById_QueryDSL(Long eventId) {
        return Optional.ofNullable(
                query.select(event)
                        .from(event)
                        .join(event.company)
                        .fetchJoin()
                        .where(event.id.eq(eventId))
                        .fetchOne());
    }


    //    결제 상세페이지
    public Optional<Event> findEventPayById_QueryDSL(Long memberId, Long eventId) {
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

//    내가 쓴 이벤트 게시판 (기업!)
    @Override
    public Page<Event> findEventListByCompanyId_QueryDSL(Pageable pageable, Long companyId) {
            List<Event> events = query.select(event)
                    .from(event)
                    .join(event.company).fetchJoin()
                    .join(event.eventFiles).fetchJoin()
                    .where(event.company.id.eq(companyId))
                    .orderBy(event.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            Long count = query.select(event.count()).from(event).where(event.company.id.eq(companyId)).fetchOne();


            return new PageImpl<>(events, pageable, count);
        }

    @Override
    public Page<Purchase> findEventListByMemberId_QueryDSL(Pageable pageable, Long memberId) {
        List<Purchase> purchases = query.select(purchase)
                .from(purchase)
                .join(purchase.event).fetchJoin()
                .where(purchase.member.id.eq(memberId))
                .orderBy(purchase.event.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(purchase.event.count()).from(purchase).where(purchase.member.id.eq(memberId)).fetchOne();


        return new PageImpl<>(purchases, pageable, count);
    }


}


