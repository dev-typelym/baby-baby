package com.app.babybaby.controller.memberController;

import com.app.babybaby.domain.memberDTO.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class OAuthController {
    @GetMapping("/")
    public RedirectView oAuthLogin(HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        log.info("==================================================");
        log.info(memberDTO.toString());
        return new RedirectView("/main/main");
    }
}
