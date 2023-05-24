package com.app.babybaby.repository.purchase.purchase;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.EntityGraph;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.babybaby.entity.purchase.purchase.QPurchase.purchase;

@RequiredArgsConstructor
public class PurchaseQueryDslImpl implements PurchaseQueryDsl {
    private final JPAQueryFactory query;

//    구매내역
    @Override
    @EntityGraph
    public Page<Purchase> findAllByMemberPaymentWithPage_QueryDSL(Pageable pageable, Long memberId) {
        List<Purchase> purchases = query.selectDistinct(purchase)
                .from(purchase)
                .leftJoin(purchase.event).fetchJoin()
                .leftJoin(purchase.event.eventFiles)
                .where(purchase.member.id.eq(memberId))
                .orderBy(purchase.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(purchase.count()).from(purchase).where(purchase.member.id.eq(memberId)).fetchOne();
        return new PageImpl<>(purchases, pageable, count);
    }

//    내가 참여한 이벤트 목록
    @Override
    public Page<Purchase> findAllByEventWithPage_QueryDSL(Pageable pageable, Long memberId, LocalDateTime startDate) {
        List<Purchase> purchases = query.select(purchase)
                .from(purchase)
                .leftJoin(purchase.event).fetchJoin()
                .leftJoin(purchase.event.eventFiles)
                .leftJoin(purchase.event.calendar)
                .where(purchase.member.id.eq(memberId).and(purchase.event.calendar.startDate.eq(startDate)))
                .orderBy(purchase.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(purchase.count()).from(purchase).where(purchase.member.id.eq(memberId)).fetchOne();
        return new PageImpl<>(purchases, pageable, count);
    }



    @Override
    public Page<Purchase> findAllByMemberPaymentFileWithPage_QueryDSL(Pageable pageable, Long memberId) {
        List<Purchase> purchases = query.selectDistinct(purchase)
                .from(purchase)
                .leftJoin(purchase.event.eventFiles).fetchJoin()
                .where(purchase.member.id.eq(memberId))
                .orderBy(purchase.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(purchase.count()).from(purchase).where(purchase.member.id.eq(memberId)).fetchOne();
        return new PageImpl<>(purchases, pageable, count);
    }

    @Override
    public Purchase findMemberIdByPaymentDetail_QueryDSL(Long PurchaseId) {
        return query.select(purchase)
                .from(purchase)
                .join(purchase.member).fetchJoin()
                .join(purchase.event).fetchJoin()
                .leftJoin(purchase.event.eventFiles)
                .where(purchase.id.eq(PurchaseId))
                .fetchOne();
    }

    @Override
    public Long findMemberByIdWithCount(Long memberId) {
        return null;
    }


}
