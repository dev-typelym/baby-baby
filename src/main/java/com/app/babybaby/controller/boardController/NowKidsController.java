package com.app.babybaby.controller.boardController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/nowKid/*")
public class NowKidsController {
    @GetMapping("multi")
    public String writeNowKidFiles(){
        return "/nowKids/now-kids-write-multi";
    }
}
