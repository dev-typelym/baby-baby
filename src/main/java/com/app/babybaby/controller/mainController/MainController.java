package com.app.babybaby.controller.mainController;

import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.service.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MemberService memberService;


    @GetMapping("main")
    public String goToMain(Model model){
        Member member = memberService.getMemberById(1L).orElseGet(null);
        MemberDTO memberDTO = memberService.toMemberDTO(member);
        model.addAttribute("member", memberDTO);
        log.info("member: " + memberDTO.toString());
        log.info("model: " + model.getAttribute("member"));
        return "main/main";
    }
}
