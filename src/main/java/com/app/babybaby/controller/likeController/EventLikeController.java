package com.app.babybaby.controller.likeController;


import com.app.babybaby.entity.like.eventLike.EventLike;
import com.app.babybaby.service.like.eventLike.EventLikeService;
import com.app.babybaby.service.like.nowKidsLike.NowKidsLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/eventLikes/*")
@RequiredArgsConstructor
@Slf4j
public class EventLikeController {

    private final EventLikeService eventLikeService;

    @PostMapping("save")
    @ResponseBody
    public Boolean clickLike(Long eventId, Boolean isLike){
        Boolean result = null;
        Long sessionId = 2L;
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
