package com.app.babybaby.controller.likeController;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/nowKidsLikes/*")
public class NowKidsLikeController {
    private NowKidsLike nowKidsLike;

    @PostMapping("save")
    @ResponseBody
    public String clickLike(NowKidsDTO nowKidsDTO){
    log.info(nowKidsDTO.toString());

    return "안료";
    }

}
