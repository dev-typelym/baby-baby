package com.app.babybaby.service.alert.alertFollow;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.repository.alert.alertFollow.AlertFollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertFollowServiceImpl implements AlertFollowService {

    private final AlertFollowRepository alertFollowRepository;

    @Override
    public List<AlertFollow> find8DescByMemberId(HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//        현재 로그인한 아이디
        Long memberId = memberDTO.getId();

        return null;
    }

    @Override
    public Long getNoReadAlert() {
        Long count = alertFollowRepository.getNoReadAlert();
        return count;
    }
}
