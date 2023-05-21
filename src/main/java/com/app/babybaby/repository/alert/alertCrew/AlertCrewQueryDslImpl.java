package com.app.babybaby.repository.alert.alertCrew;

import com.app.babybaby.entity.alert.alertCrew.AlertCrew;
import com.app.babybaby.entity.alert.alertCrew.QAlertCrew;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.type.CategoryType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.app.babybaby.entity.alert.alertCrew.QAlertCrew.alertCrew;
import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;
@RequiredArgsConstructor
@Slf4j
public class AlertCrewQueryDslImpl implements AlertCrewQueryDsl {

    private final JPAQueryFactory query;

    @Override
    public List<String> getAlertList(Long id) {
        List<String> alert = query.select(alertCrew.alertContent)
                .from(alertCrew)
                .where(alertCrew.id.eq(id))
                .orderBy(alertCrew.id.desc())
                .limit(8)
                .fetch();
        return alert;
    }
}
