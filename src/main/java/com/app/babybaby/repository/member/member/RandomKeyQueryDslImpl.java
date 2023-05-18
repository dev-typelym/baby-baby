package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.member.QMember;
import com.app.babybaby.entity.member.QRandomKey;
import com.app.babybaby.entity.member.RandomKey;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.babybaby.entity.member.QMember.member;
import static com.app.babybaby.entity.member.QRandomKey.randomKey1;

@RequiredArgsConstructor
public class RandomKeyQueryDslImpl implements RandomKeyQueryDsl {
    private final JPAQueryFactory query;

    /* 회원의 가장 최근 랜덤키 찾기 */
    @Override
    public RandomKey getLatestRandomKey(Long id) {
        return query.select(randomKey1)
                .from(member)
//                .join(member.randomKeys, randomKey1)
                .where(member.id.eq(id))
                .orderBy(randomKey1.id.desc())
                .fetchFirst();
    }
}
