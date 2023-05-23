package com.app.babybaby.controller.memberController;


import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.service.member.follow.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/follows/*")
@RequiredArgsConstructor
@Slf4j
public class FollowController {

    private final FollowService followService;

    @PostMapping("save")
    public void saveFollows(Long memberId, Boolean isFollowed, HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Long sessionId = memberDTO.getId();
        log.info("isFollowed는 : " + isFollowed);

        if(!isFollowed){
            followService.saveFollow(memberId, sessionId);
        } else if(isFollowed){
            log.info("여기도 안들어옴?");
            followService.deleteFollow(memberId, sessionId);
        }
    }
}
