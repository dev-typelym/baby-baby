package com.app.babybaby.repository.alert;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.alert.QAlert;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.type.AlertReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.app.babybaby.entity.alert.QAlert.alert;
import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;

@RequiredArgsConstructor
@Slf4j
public class AlertQueryDslImpl implements AlertQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<Alert> getNoReadAlertList() {
        List<Alert> alertList = query.select(alert)
                .from(alert)
                .orderBy(alert.id.desc())
                .where(alert.readStatus.eq(AlertReadStatus.UNREAD))
                .fetch();
        return alertList;
    }


}

