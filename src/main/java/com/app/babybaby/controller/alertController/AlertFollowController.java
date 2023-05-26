package com.app.babybaby.controller.alertController;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.service.alert.alertFollow.AlertFollowService;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    private Long getSessionMemberId(){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        return memberDTO.getId();
    }


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
    @GetMapping("list")
    @ResponseBody
//    @Transactional(rollbackFor = Exception.class)
    public List<MemberDTO> getAlertList() {
//        log.info(getSessionMemberId() + " getSession멤버 아이디");
//        log.info("=========================",alertFollowService.find8RecentFollowersByMemberId(getSessionMemberId()).toString());
//        log.info(alertFollowService.find8RecentFollowersByMemberId(getSessionMemberId()).toString());
        List<MemberDTO> result = alertFollowService.find8RecentFollowersByMemberId(getSessionMemberId());
        List<MemberDTO> followerList = (List<MemberDTO>)session.getAttribute("followers");
        log.info("result=============d==d==d==d===" + result.toString());
        return result;
    }


//    헤더 빨간 점 UNREAD를 READ로 바꾸기
//    @GetMapping("read")
//    public List<Long> updateReadStatus(){
//        log.info("드러렁쿠울루우우루룰");
//        List<MemberDTO> result = (List<MemberDTO>)session.getAttribute("followers");
//        List<Long> uniqueIds = new ArrayList<>();
//        String read = "읽음";
//        // 기존 리스트에서 중복되지 않는 id 값만 추출하여 새로운 리스트에 추가
//        for (MemberDTO member : result) {
//            Long id = member.getId();
//            if (!uniqueIds.contains(id)) {
//                uniqueIds.add(id);
//                read = "읽지않음";
////                return read;
//            }
//        }
//        log.info("uniqueIds : " + uniqueIds);
//        return uniqueIds;
//    }
}
