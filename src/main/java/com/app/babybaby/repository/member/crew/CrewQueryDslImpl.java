package com.app.babybaby.repository.member.crew;

import com.app.babybaby.entity.member.Kid;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.babybaby.entity.member.QKid.kid;

@RequiredArgsConstructor
public class CrewQueryDslImpl implements CrewQueryDsl {
    private final JPAQueryFactory query;

    //    통솔자 아이들 조회
    @Override
    public List<Kid> findKidByUserId(Long userId) {
        return query.select(kid).from(kid).where(kid.parent.id.eq(userId)).orderBy(kid.id.desc()).fetch();
    }


}
