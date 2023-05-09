package com.app.babybaby.repository.user.kid;

import com.app.babybaby.entity.user.Kid;
import com.app.babybaby.entity.user.QKid;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.babybaby.entity.user.QKid.kid;

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
}
