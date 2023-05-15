package com.app.babybaby.repository.like.nowKidsLike;

import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.entity.like.nowKidsLike.QNowKidsLike;
import com.app.babybaby.entity.member.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
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

    @Override
    public boolean hasMemberLikedNowKids(Long memberId) {
        BooleanExpression hasMemberLiked = nowKidsLike.member.id.eq(memberId);
        return query.selectFrom(nowKidsLike)
                .where(hasMemberLiked)
                .fetchFirst() != null;
    }


}
