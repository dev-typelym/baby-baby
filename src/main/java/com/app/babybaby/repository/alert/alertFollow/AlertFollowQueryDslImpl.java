package com.app.babybaby.repository.alert.alertFollow;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.entity.alert.alertFollow.QAlertFollow;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.QMember;
import com.app.babybaby.type.AlertReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.babybaby.entity.alert.alertFollow.QAlertFollow.alertFollow;
import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;
import static com.app.babybaby.entity.member.QMember.member;

@RequiredArgsConstructor
@Slf4j
public class AlertFollowQueryDslImpl implements AlertFollowQueryDsl {
    private final JPAQueryFactory query;
    @Override
    public Long getNoReadAlert() {
        Long count = query.select(alertFollow.count()).from(alertFollow).where(alertFollow.readStatus.eq(AlertReadStatus.UNREAD)).fetchOne();
        return count;
    }

    @Override
    public List<AlertFollow> find8DescByMemberId(Long memberId) {
        return null;
    }

//    @Override
//    public Page<AlertFollow> findAllByUserId(Pageable pageable, Long memberId) {
//        List<AlertFollow> alarms = query.select(alertFollow)
//                .from(alertFollow)
//                .leftJoin(alertFollow.member)
//                .fetchJoin()
//                .orderBy(alertFollow.alertRegisterDate.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize()).fetch();
//
//        long total = query.select(alertFollow.count())
//                .from(alertFollow)
//                .where(alertFollow.member.id.eq(memberId))
//                .fetchOne();
//
//        return new PageImpl<>(alarms, pageable, total);
//    }
}
