package com.app.babybaby.repository.alert.alertFollow;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.domain.alertDTO.QAlertFollowDTO;
import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.entity.alert.alertFollow.QAlertFollow;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.QFollow;
import com.app.babybaby.entity.member.QMember;
import com.app.babybaby.type.AlertReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import static com.app.babybaby.entity.alert.alertFollow.QAlertFollow.alertFollow;
import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;
import static com.app.babybaby.entity.member.QFollow.follow;
import static com.app.babybaby.entity.member.QMember.member;

@RequiredArgsConstructor
@Slf4j
public class AlertFollowQueryDslImpl implements AlertFollowQueryDsl {
    private final JPAQueryFactory query;
    @Override
    public List<Member> getFollowers(Long memberId) {
        java.sql.Date currentDate = new java.sql.Date(new Date().getTime());
        List<Member> followers = query.select(follow.follower)
                .from(follow)
                .join(follow.follower)
                .where(
                        follow.follower.id.eq(memberId)
                                .and(
                                        follow.followDate.year().eq(currentDate.toLocalDate().getYear())
                                                .and(follow.followDate.month().eq(currentDate.toLocalDate().getMonthValue()))
                                                .and(follow.followDate.dayOfMonth().eq(currentDate.toLocalDate().getDayOfMonth()))
                                )
                )
                .fetch();
        return followers;
    }

    @Override
    public List<AlertFollow> find8DescByMemberId(Long sessionMemberId) {
        List<AlertFollow> alertFollowDTOS = query.select(alertFollow)
                .from(alertFollow)
                .leftJoin(alertFollow.member)
                .fetchJoin()
                .where(alertFollow.member.id.eq(sessionMemberId))
                .orderBy(alertFollow.id.desc())
                .limit(8)
                .fetch();

//         잘 끌고옴
        log.info("레에포오오오오오아이디" + sessionMemberId);

        log.info("레에포오오오오오내용" + alertFollowDTOS);
        return alertFollowDTOS;
    }

    @Override
    public List<Follow> find8RecentFollowersByMemberId(Long memberId) {

        return query.select(follow)
                .from(follow)
                .join(follow.following)
                .fetchJoin()
                .where(follow.following.id.eq(memberId))
                .orderBy(follow.id.desc())
                .limit(8)
                .fetch();
    }




}
