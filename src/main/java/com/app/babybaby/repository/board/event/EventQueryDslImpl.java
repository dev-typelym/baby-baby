package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.like.eventLike.QEventLike;
import com.app.babybaby.entity.purchase.coupon.QCoupon;
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
    @Override
    public Page<Event> findEventList(Pageable pageable) {
        List<Event> events = query.select(event).from(event).orderBy(event.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Long count = query.select(event.id.count()).from(event).fetchOne();


        return new PageImpl<>(events, pageable, count);
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
