package com.app.babybaby.repository.like.nowKidsLike;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.board.nowKids.QNowKids;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.entity.like.nowKidsLike.QNowKidsLike;
import com.app.babybaby.entity.member.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.babybaby.entity.board.nowKids.QNowKids.nowKids;
import static com.app.babybaby.entity.like.eventLike.QEventLike.eventLike;
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
    public boolean hasMemberLikedNowKids(Long memberId, Long nowKidsId) {
        BooleanExpression hasMemberLiked = nowKidsLike.member.id.eq(memberId);
        BooleanExpression hasEventLike = nowKidsLike.nowKids.id.eq(nowKidsId);
        return query.selectFrom(nowKidsLike)
                .where(hasMemberLiked)
                .where(hasEventLike)
                .fetchFirst() != null;
    }

//    내가 좋아요한 목록
    @Override
    public Slice<NowKids> findAllByMemberLikesWithPaging_QueryDSL(Pageable pageable, Long memberId) {
        List<NowKids> list = query.select(nowKidsLike.nowKids)
                .from(nowKidsLike)
                .leftJoin(nowKidsLike).fetchJoin()
                .leftJoin(nowKidsLike.nowKids.nowKidsFile).fetchJoin()
                .where(nowKidsLike.member.id.eq(memberId))
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (list.size() > pageable.getPageSize()) {
            hasNext = true;
            list.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(list, pageable, hasNext);
    }





}
