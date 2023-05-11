package com.app.babybaby.repository.like.eventLike;

import com.app.babybaby.entity.board.event.Event;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.babybaby.entity.like.eventLike.QEventLike.eventLike;

@RequiredArgsConstructor
public class EventLikeQueryDslImpl implements EventLikeQueryDsl {
    private final JPAQueryFactory query;

    //    이벤트 게시판 좋아요 누른 게시물 리스트
    @Override
    public Slice<Event> findAllByMemberLikesWithPaging_QueryDSL(Pageable pageable, Long memberId) {
        List<Event> list = query.select(eventLike.event)
                .from(eventLike)
                .where(eventLike.member.id.eq(memberId))
                .leftJoin(eventLike.event.eventFiles)
                .join(eventLike.event.company)
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
