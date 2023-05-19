package com.app.babybaby.controller.memberController;

import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.service.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OAuthController {

    private final MemberService memberService;

    @GetMapping("/")
    public RedirectView oAuthLogin(HttpSession session, RedirectAttributes redirectAttributes){
        log.info(" ------------------- 로그인 처리 후 맨 마지막 컨트롤러 ------------------------------------- ");
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        if (memberDTO.getId() == null) {
            redirectAttributes.addFlashAttribute("member", memberDTO);
            return new RedirectView("/member/join/general");
        }
        log.info("==================================================");
        log.info(memberDTO.toString());
        return new RedirectView("/main/main");

    }
}
