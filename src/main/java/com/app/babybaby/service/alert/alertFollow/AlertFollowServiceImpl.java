package com.app.babybaby.service.alert.alertFollow;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.repository.alert.alertFollow.AlertFollowRepository;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertFollowServiceImpl implements AlertFollowService {

    private final AlertFollowRepository alertFollowRepository;
    private final HttpSession session;
    
//    헤더 팔로우 알림 목록 8개
    @Override
    public List<AlertFollowDTO> find8DescByMemberId() {

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//        현재 로그인한 아이디
        Long memberId = memberDTO.getId();

        List<AlertFollow> alerts = alertFollowRepository.find8DescByMemberId(memberId);
        log.info(alerts.toString());
        List<AlertFollowDTO> alertList = alerts.stream().map(alertFollow -> toAlertFollowDTO(alertFollow)).collect(Collectors.toList());
        return alertList;
    }

    @Override
    public Long getNoReadAlert() {
        Long count = alertFollowRepository.getNoReadAlert();
        return count;
    }

//    알림 id를 받아와서 해당 알림을 읽은 상태로 변경
    @Override
    public void updateReadStatus(Long id) {
        alertFollowRepository.findById(id).get().updateStatus();
    }
}
