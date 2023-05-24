package com.app.babybaby.controller.alertController;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.service.alert.alertFollow.AlertFollowService;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/alert/follow/controller/*")
@RequiredArgsConstructor
@Slf4j
public class AlertFollowController {

    private final AlertFollowService alertFollowService;
    private final MemberService memberService;
    private final HttpSession session;

    private Long getMemberIdByEmail(String memberEmail){
        return memberService.findByMemberEmail(memberEmail).getId();
    }

99999999999999999999999999999999999999999999999999999999999999999999999



//    헤더 알림 갯수
    @GetMapping("unread")
    @ResponseBody
    public Long getUnread(HttpServletRequest request) {
//        Long count = (Long)request.getSession().getAttribute("noReadAlarm");
        Long count = (Long)request.getAttribute("noReadAlarm");
//        테스트 용도
        return count;
    }
    
//    헤더 알림 목록
//    @GetMapping("list")
//    @ResponseBody
////    @Transactional(rollbackFor = Exception.class)
//    public List<MemberDTO> getAlertList() {
//        log.info("드러렁쿠울");
//        log.info(getSessionMemberId() + " getSession멤버 아이디");
////        log.info("=========================",alertFollowService.find8RecentFollowersByMemberId(getSessionMemberId()).toString());
////        log.info(alertFollowService.find8RecentFollowersByMemberId(getSessionMemberId()).toString());
//        List<MemberDTO> result = alertFollowService.find8RecentFollowersByMemberId(getSessionMemberId());
//        log.info(result.toString());
//        return result;
//    }

//    @GetMapping("list")
//    @ResponseBody
//    public List<AlertFollowDTO> getAlertFollowList() {
//        log.info("드러렁쿠울");
//        log.info(getSessionMemberId() + " getSession멤버 아이디");
////        log.info("=========================",alertFollowService.find8RecentFollowersByMemberId(getSessionMemberId()).toString());
////        log.info(alertFollowService.find8RecentFollowersByMemberId(getSessionMemberId()).toString());
//        List<AlertFollowDTO> result = alertFollowService.find8DescByMemberId(getSessionMemberId());
//        log.info("결과아아아아아ㅏ"+result.toString());
//        return result;
//    }
}
