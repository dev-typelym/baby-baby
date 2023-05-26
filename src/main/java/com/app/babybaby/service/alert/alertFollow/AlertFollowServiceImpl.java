package com.app.babybaby.service.alert.alertFollow;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.CompanyDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.domain.memberDTO.MemberDetailDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.alert.alertFollow.AlertFollowRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.type.AlertReadStatus;
import com.app.babybaby.type.AlertType;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AlertFollowServiceImpl implements AlertFollowService {

    private final AlertFollowRepository alertFollowRepository;
    private final MemberService memberService;
    private final HttpSession session;

//    @Override
//    public List<MemberDTO> getFollowers(Long memberId) {
//        List<MemberDTO> followers = new ArrayList<>();
//        alertFollowRepository.getFollowers(3L).forEach(member -> {
//            followers.add(entityToMemberDTO(member));
//        });
//        return followers;
//    }

        @Override
    public List<MemberDTO> getFollowers(Long memberId) {
        List<MemberDTO> followers = new ArrayList<>();
        alertFollowRepository.getFollowers(3L).forEach(member -> {
            followers.add(entityToMemberDTO(member));
        });
        return followers;
    }


    //    헤더 팔로우 알림 목록 8개
//    @Override
//    public List<AlertFollowDTO> find8DescByMemberId(Long sessionId) {
////        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//////        현재 로그인한 아이디
////        Long memberId = memberDTO.getId();
//        List<AlertFollow> alerts = alertFollowRepository.find8DescByMemberId(sessionId);
//        log.info("alerts============================");
//        log.info(alerts.toString());
//        List<AlertFollowDTO> alertList = alerts.stream().map(alertFollow -> toAlertFollowDTO(alertFollow)).collect(Collectors.toList());
//        return alertList;
//    }

//    @Override
//    public Long getNoReadAlert() {
//        Long count = alertFollowRepository.getNoReadAlert();
//        return count;
//    }

//    알림 id를 받아와서 해당 알림을 읽은 상태로 변경
    @Override
    public void updateReadStatus(Long id) {
        alertFollowRepository.findById(id).get().updateStatus();
    }

    @Override
    public List<MemberDTO> find8RecentFollowersByMemberId(Long memberId) {
        log.info("=======최근 8명 불러오기=========memberId",memberId);
        List<Follow> list = alertFollowRepository.find8RecentFollowersByMemberId(memberId);
        log.info("리스트으으으123" + list);
        return list.stream().map(r -> entityToMemberDTO(r.getFollower())).collect(Collectors.toList());
    }

//    @Override
//    public List<CompanyDTO> find8RecentFollowersByMemberId2(Long memberId) {
//        log.info("=======최근 8명 불러오기=========memberId",memberId);
//        List<Follow> list = alertFollowRepository.find8RecentFollowersByMemberId(memberId);
//        log.info("리스트으으으123" + list);
//        return list.stream().map(r -> this.toCompanyDTO(r.getFollower())).collect(Collectors.toList());
//    }

    @Override
    public AlertFollow saveAlertDTOForFollowing(Follow follow) {
        AlertFollow alertFollow = AlertFollow.builder()
                .alertTitle("팔로우 알림")
                .alertContent(follow.getFollower()+"님이 당신을 팔로우 중 입니다.")
                .alertReadStatus(AlertReadStatus.UNREAD)
                .alertType(AlertType.FOLLOW)
                .alertRegisterDate(LocalDateTime.now())
                .follower(follow)
                .member(follow.getFollowing())
                .build();
        return alertFollow;
    }


    @Override
    public AlertFollowDTO findAlertDTOForFollowing(Long memberId) {



        return null;
    }

    @Override
    public void updateAlertReadStatus(AlertFollow alertFollow) {
        alertFollow.updateStatus();
    }
}
