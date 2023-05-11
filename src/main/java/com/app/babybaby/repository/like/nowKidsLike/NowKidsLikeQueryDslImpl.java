package com.app.babybaby.repository.like.nowKidsLike;

import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.entity.like.nowKidsLike.QNowKidsLike;
import com.app.babybaby.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.babybaby.entity.like.nowKidsLike.QNowKidsLike.nowKidsLike;
import static com.app.babybaby.entity.member.QMember.member;

@RequiredArgsConstructor
public class NowKidsLikeQueryDslImpl implements NowKidsLikeQueryDsl {
    private final JPAQueryFactory query;

    public List<NowKidsLike> findAllNowKidsLikeByMemberId_QueryDsl(Long sessionId){
        return query.selectFrom(nowKidsLike)
                .join(nowKidsLike.member)
                .where(nowKidsLike.member.id.eq(sessionId))
                .fetch()
                ;
    }
}
