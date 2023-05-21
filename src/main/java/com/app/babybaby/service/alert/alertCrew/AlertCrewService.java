package com.app.babybaby.service.alert.alertCrew;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.entity.alert.alertCrew.AlertCrew;
import com.app.babybaby.type.CategoryType;

import java.util.List;

public interface AlertCrewService {
    public List<String> getAlertList(Long id);
}
