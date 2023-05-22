package com.app.babybaby.repository.alert.alertCrew;

import com.app.babybaby.entity.alert.alertCrew.AlertCrew;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.type.CategoryType;

import java.util.List;

public interface AlertCrewQueryDsl {

    public List<String> getAlertList(Long id);
}
