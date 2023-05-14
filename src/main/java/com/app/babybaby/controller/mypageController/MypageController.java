package com.app.babybaby.controller.mypageController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {


    @GetMapping("review")
    public String getReview(Model model){
        model.addAttribute("review");
        return "myPage/myPage-review";
    }
}
