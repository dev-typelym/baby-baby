package com.app.babybaby.controller.memberController;


import com.app.babybaby.service.member.follow.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/follows/*")
@RequiredArgsConstructor
@Slf4j
public class FollowController {

    private final FollowService followService;

    @PostMapping("save")
    public void saveFollows(Long memberId, Boolean isFollowed){
        Long sessionId = 7L;
        log.info("isFollowed는 : " + isFollowed);

        if(!isFollowed){
            followService.saveFollow(sessionId, memberId);
        } else if(isFollowed){
            log.info("여기도 안들어옴?");
            followService.deleteFollow(sessionId, memberId);
        }
    }
}
