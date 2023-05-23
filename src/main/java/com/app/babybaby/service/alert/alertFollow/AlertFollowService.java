package com.app.babybaby.service.alert.alertFollow;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AlertFollowService {

//    알림 목록
    public List<AlertFollow> find8DescByMemberId(HttpSession session);

//    읽지 않은 알림 수
    public Long getNoReadAlert();
}
