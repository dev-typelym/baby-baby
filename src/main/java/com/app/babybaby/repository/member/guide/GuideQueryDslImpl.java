package com.app.babybaby.repository.member.guide;

import com.app.babybaby.entity.board.event.QEvent;
import com.app.babybaby.entity.member.Guide;
import com.app.babybaby.entity.member.QGuide;
import com.app.babybaby.repository.guideSchedule.GuideScheduleQueryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.member.QGuide.guide;

@RequiredArgsConstructor
public class GuideQueryDslImpl implements GuideQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<Guide> findEventById(Long guideId, Long eventId) {
        return query.select(guide)
                .from(guide)
                .join(guide.event)
                .fetchJoin()
                .where(guide.id.eq(guideId),guide.event.id.eq(eventId)).fetch();
    }

}
