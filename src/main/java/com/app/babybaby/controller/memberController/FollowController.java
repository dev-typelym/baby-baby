package com.app.babybaby.controller.memberController;


import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.service.member.follow.FollowService;
import com.app.babybaby.service.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/follows/*")
@RequiredArgsConstructor
@Slf4j
public class FollowController {

    private final FollowService followService;
    private final HttpSession session;

    private Long getSessionMemberId(){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        return memberDTO.getId();
    }

    @PostMapping("save")
    public void saveFollows(Long memberId, Boolean isFollowed, HttpSession session){
        Long sessionId = getSessionMemberId();
        log.info("isFollowed는 : " + isFollowed);

        if(!isFollowed){
            followService.saveFollow(memberId, sessionId);
        } else if(isFollowed){
            log.info("여기도 안들어옴?");
            followService.deleteFollow(memberId, sessionId);
        }
    }

//    나를 팔로우 중인 사람들
    @PostMapping("list/followers")
    public Page<MemberDTO> getFollowers(@RequestParam("page") Integer page){
        Page<MemberDTO> result = followService.findFollowersByMemberId(
                PageRequest.of(page, 20), getSessionMemberId());
        return result;
    }

//    팔로잉 중인 사람들
    @PostMapping("list/followings")
    public Page<MemberDTO> getFollowings(@RequestParam("page") Integer page){
        Page<MemberDTO> result = followService.findFollowingsByMemberId(
                PageRequest.of(page, 20), getSessionMemberId());
        return result;
    }

    @PostMapping("isFollowed")
    public Boolean isFollowed(@RequestParam("memberId") Long memberId){
        return followService.getIsFollowedByMemberId(memberId, getSessionMemberId());
    }

    @PostMapping("countFollowers")
    public Long countFollowers(@RequestParam("memberId")Long memberId){
        return followService.findFollowingMemberCountByMemberId_QueryDSL(memberId);
    }
}
