package com.app.babybaby.controller.board;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/parent/*")
public class ParentBoardController {
    @GetMapping("write")
    public String parentWrite(){
        return "parents-yard-board/parents-yard-board-write";
    }
}
