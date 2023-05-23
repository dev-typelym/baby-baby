package com.app.babybaby.repository.member.crew;

import com.app.babybaby.entity.board.event.QEvent;
import com.app.babybaby.entity.member.Crew;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.QCrew;
import com.app.babybaby.entity.member.QGuide;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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
    public List<Crew> findCrewByMemberId(Long memberId, String date) {
        return query.select(crew)
                .from(crew)
//                .join(crew.guide).fetchJoin() // fetch join for guide
//                .join(crew.kid).fetchJoin() // fetch join for kid
                .join(crew.guide.event).fetchJoin() // fetch join for event
                .where(crew.guide.id.eq(memberId))
                .fetch();

    }

}
