package com.app.babybaby.service.alert.alertFollow;

import com.app.babybaby.repository.alert.alertFollow.AlertFollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertFollowServiceImpl implements AlertFollowService {

    private final AlertFollowRepository alertFollowRepository;

    @Override
    public Long getNoReadAlert() {
        Long count = alertFollowRepository.getNoReadAlert();
        return count;
    }
}
