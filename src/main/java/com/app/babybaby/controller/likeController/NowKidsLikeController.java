package com.app.babybaby.controller.likeController;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.like.nowKidsLike.NowKidsLikeRepository;
import com.app.babybaby.service.board.nowKids.NowKidsService;
import com.app.babybaby.service.like.nowKidsLike.NowKidsLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/nowKidsLikes/*")
public class NowKidsLikeController {
    private final NowKidsLikeService nowKidsLikeService;

    @PostMapping("save")
    @ResponseBody
    public Boolean clickLike(Long nowKidsId, Boolean isLike, HttpSession session){
        Boolean result = null;
        Long sessionId = null;
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        if(memberDTO != null){
            sessionId = memberDTO.getId();
        }
        log.info(memberDTO.toString());
//        isLike가 false라는 뜻은 아직 좋아요가 눌리지 않은 상태
        if(sessionId != null){
            if(!isLike){
                nowKidsLikeService.likeSave(nowKidsId, sessionId);
                result = true;
            } else{
                nowKidsLikeService.deleteLike(nowKidsId, sessionId);
                result = false;
            }
        }
        return result;
    }

}
