package com.app.babybaby.service.alert.alertFollow;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public interface AlertFollowService {

//    헤더 팔로우 알림 목록 8개
    public List<AlertFollowDTO> find8DescByMemberId();

//    읽지 않은 알림 수
    public Long getNoReadAlert();

//    알림 상태 변경
    public void updateReadStatus(Long id);

    default AlertFollowDTO toAlertFollowDTO(AlertFollow alertFollow) {
        return AlertFollowDTO.builder()
                .alertContent(alertFollow.getMember().getMemberNickname()+"님이 나를 팔로우하기 시작했습니다!")
                .memberNickname(alertFollow.getMember().getMemberNickname())
                .id(alertFollow.getId())
                .memberId(alertFollow.getMember().getId())
                .alertReadStatus(alertFollow.getReadStatus())
                .build();
    }
}
