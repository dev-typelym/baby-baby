package com.app.babybaby.controller.mypageController;

import com.app.babybaby.service.board.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    @Autowired
    private final ReviewService reviewService;

    @GetMapping("review")
    public String getReview(Model model){
        model.addAttribute("review",reviewService.findReviewById(1L));
        return "myPage/myPage-review";
    }
}
