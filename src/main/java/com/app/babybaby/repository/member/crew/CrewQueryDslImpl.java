package com.app.babybaby.repository.member.crew;

import com.app.babybaby.domain.memberDTO.EventKidDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.event.QEvent;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.QCrew;
import com.app.babybaby.entity.member.QGuide;
import com.app.babybaby.entity.member.QKid;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.babybaby.entity.member.QCrew.crew;
import static com.app.babybaby.entity.member.QGuide.guide;
import static com.app.babybaby.entity.member.QKid.kid;

@RequiredArgsConstructor
public class CrewQueryDslImpl implements CrewQueryDsl {
    private final JPAQueryFactory query;

    //    통솔자 아이들 조회
    @Override
    public List<Kid> findKidByUserId(Long userId) {
        return query.select(kid).from(kid).where(kid.parent.id.eq(userId)).orderBy(kid.id.desc()).fetch();
    }

    @Override
    public List<Tuple> findCrewByMemberId(Long sessionId, String date) {
//        return query.select(QEvent.event,
//                JPAExpressions.select(kid)
//                        .from(crew)
//                        .join(kid)
//                        .on(crew.kid.id.eq(kid.id).and(crew.eventRegisterDate.eq(date)))
//                        .join(guide)
//                        .on(crew.guide.id.eq(guide.id).and(guide.generalGuide.id.eq(sessionId).or(guide.adminGuide.id.eq(sessionId)))
//                        )
//                        .from(crew)
//                        .join(guide)
//                        .on(crew.guide.id.eq(guide.id).and(crew.eventRegisterDate.eq(date)).and(guide.generalGuide.id.eq(sessionId).or(guide.adminGuide.id.eq(sessionId))))
//                        .join(QEvent.event)
//                        .on(guide.event.id.eq(QEvent.event.id)))
//                .fetch();

        return query.select(QEvent.event,
                JPAExpressions.select(kid)
                        .from(crew)
                        .join(kid)
                        .on(crew.kid.id.eq(kid.id).and(crew.eventRegisterDate.eq(date)))
                        .join(guide)
                        .on(crew.guide.id.eq(guide.id).and(guide.generalGuide.id.eq(sessionId).or(guide.adminGuide.id.eq(sessionId))))
                     )
                        .from(crew)
                        .join(guide)
                        .on(crew.guide.id.eq(guide.id).and(crew.eventRegisterDate.eq(date)).and(guide.generalGuide.id.eq(sessionId).or(guide.adminGuide.id.eq(sessionId))))
                        .join(QEvent.event)
                        .on(guide.event.id.eq(QEvent.event.id))
                        .fetch();
//        return query.select(new EventKidDTO(Event, Kid))
//                        .from(crew)
//                        .join(guide)
//                        .on(crew.guide.id.eq(guide.id).and(crew.eventRegisterDate.eq(date)).and(guide.generalGuide.id.eq(sessionId).or(guide.adminGuide.id.eq(sessionId))))
//                        .join(QEvent.event)
//                        .on(guide.event.id.eq(QEvent.event.id))
//                        .fetch();

    }




    }//end