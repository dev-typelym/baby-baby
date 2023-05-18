package com.app.babybaby.controller.boardController;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/review/*")
public class ReviewController {

    @GetMapping("writeFirst")
    public String goReviewBoardWrite(){
        return "review-board/review-board-write";
    }

    @GetMapping("writeSecond")
    public String goReviewBoardSecond(){
        return "review-board/review-board-thumbnail";
    }

    @GetMapping("detail")
    public String goReviewDetail(){
        return "review-board/review-board-detail";
    }

    @GetMapping("list")
    public String goListDetail(){
        return "review-board/review-board";
    }
}
