package com.app.babybaby.service.alert.alertCrew;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.entity.alert.alertCrew.AlertCrew;
import com.app.babybaby.repository.alert.alertCrew.AlertCrewRepository;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertCrewServiceImpl implements AlertCrewService {

    private final AlertCrewRepository alertCrewRepository;

    @Override
    public List<String> getAlertList(Long id) {
        List<String> alert = alertCrewRepository.getAlertList(id);

        return alert;
    }
}
