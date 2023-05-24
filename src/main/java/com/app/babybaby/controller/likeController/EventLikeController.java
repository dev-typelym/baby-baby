package com.app.babybaby.controller.likeController;


import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.like.eventLike.EventLike;
import com.app.babybaby.service.like.eventLike.EventLikeService;
import com.app.babybaby.service.like.nowKidsLike.NowKidsLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/eventLikes/*")
@RequiredArgsConstructor
@Slf4j
public class EventLikeController {

    private final EventLikeService eventLikeService;

    @PostMapping("save")
    @ResponseBody
    public Boolean clickLike(Long eventId, Boolean isLike, HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Long sessionId = null;

        if(memberDTO != null){
            sessionId = memberDTO.getId();
        }

        Boolean result = null;
        log.info("내가 가져온 nowKidsId는  : " + String.valueOf(eventId));
        log.info("내가 가져온 isLike는  : " + String.valueOf(isLike));
//        isLike가 false라는 뜻은 아직 좋아요가 눌리지 않은 상태
        if(sessionId != null){
            if(!isLike){
                eventLikeService.likeSave(eventId, sessionId);
                result = true;
            } else{
                eventLikeService.deleteLike(eventId, sessionId);
                result = false;
            }
        }
        return result;
    }
}
