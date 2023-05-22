package com.app.babybaby.repository.like.eventLike;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.QNowKids;
import com.app.babybaby.entity.like.eventLike.EventLike;
import com.app.babybaby.entity.like.eventLike.QEventLike;
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

@RequiredArgsConstructor
public class EventLikeQueryDslImpl implements EventLikeQueryDsl {
    private final JPAQueryFactory query;

    //    이벤트 게시판 좋아요 누른 게시물 리스트
    @Override
    public Slice<EventLike> findAllByMemberLikesWithPaging_QueryDSL(Pageable pageable, Long memberId) {
        List<EventLike> list = query.selectDistinct(eventLike)
                .from(eventLike)
                .where(eventLike.member.id.eq(memberId))
                .join(eventLike.event.company)
                .join(eventLike.event.eventFiles)
                .join(eventLike.event.calendar)
                .orderBy(eventLike.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1) // hasNext를 위해 1개 더 불러옵니다
                .fetch();

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (list.size() > pageable.getPageSize()) {
            hasNext = true;
            list.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(list, pageable, hasNext);
    }

    @Override
    public boolean hasMemberLikedEvent(Long memberId, Long eventId) {
        BooleanExpression hasMemberLiked = eventLike.member.id.eq(memberId);
        BooleanExpression hasEventLike = eventLike.event.id.eq(eventId);
        return query.selectFrom(eventLike)
                .where(hasMemberLiked)
                .where(hasEventLike)
                .fetchFirst() != null;
    }


//    //    이벤트 게시판 좋아요 누른 게시물 리스트
//    @Override
//    public Slice<EventLike> findAllByMemberLikesWithPaging_QueryDSL(Pageable pageable, Long memberId) {
//        List<EventLike> list = query.select(eventLike)
//                .from(eventLike)
//                .where(eventLike.member.id.eq(memberId))
//                .join(eventLike.event.company)
//                .join(eventLike.event.eventFiles)
//                .orderBy(eventLike.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        List<EventLike> lists = query.selectDistinct(eventLike)
//                .from(eventLike)
//                .where(eventLike.in(list))
//                .fetch();
//
//        boolean hasNext = false;
//
//        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
//        if (list.size() > pageable.getPageSize()) {
//            hasNext = true;
//            list.remove(pageable.getPageSize());
//        }
//
//        return new SliceImpl<>(lists, pageable, hasNext);
//    }
}
