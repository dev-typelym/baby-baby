package com.app.babybaby.repository.member.kid;

import com.app.babybaby.entity.member.Kid;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.babybaby.entity.member.QKid.kid;
import static com.app.babybaby.entity.purchase.purchase.QPurchase.purchase;

@RequiredArgsConstructor
public class KidQueryDslImpl implements KidQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public List<Kid> findAllKidsByEventId_QueryDsl(Long eventId) {
        query.select(kid)
                .from(kid)
                .where(kid.parent.id.eq(kid.id));
        return null;
    }

    @Override
    public List<Kid> findAllMyKid_QueryDsl(Long memberId) {
        return query.select(kid)
                .from(kid)
                .where(kid.parent.id.eq(memberId))
                .fetch();
    }

    @Override
    public Long findAllMyKidCount_QueryDsl(Long memberId) {
        Long count = query.select(kid.count()).from(kid).where(kid.parent.id.eq(memberId)).fetchOne();
        return count;
    }


}
