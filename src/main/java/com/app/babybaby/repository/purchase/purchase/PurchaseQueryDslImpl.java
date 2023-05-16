package com.app.babybaby.repository.purchase.purchase;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;

import static com.app.babybaby.entity.purchase.purchase.QPurchase.purchase;

@RequiredArgsConstructor
public class PurchaseQueryDslImpl implements PurchaseQueryDsl {
    private final JPAQueryFactory query;

//    구매내역
    @Override
    public Page<Purchase> findAllByMemberPaymentWithPage_QueryDSL(Pageable pageable, Long memberId) {
        List<Purchase> purchases = query.select(purchase)
                .from(purchase)
                .join(purchase.event).fetchJoin()
                .join(purchase.event.eventFiles).fetchJoin()
                .where(purchase.member.id.eq(memberId))
                .orderBy(purchase.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(purchase.count()).from(purchase).where(purchase.member.id.eq(memberId)).fetchOne();
        return new PageImpl<>(purchases, pageable, count);
    }


}
