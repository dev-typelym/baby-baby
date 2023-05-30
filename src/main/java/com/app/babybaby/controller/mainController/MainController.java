package com.app.babybaby.controller.mainController;

import com.app.babybaby.controller.provider.UserDetail;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.service.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MemberService memberService;
    private final HttpSession session;


    @GetMapping("")
    public RedirectView goToMainRedirect(){
        return new RedirectView("main");
    }

    @GetMapping("main")
    public String goToMain(@AuthenticationPrincipal UserDetail userDetail){
        Member member = null;
        if(session.getAttribute("member")==null){
            try {
                member = memberService.findByMemberEmail(userDetail.getMemberEmail());
            } catch (NullPointerException e) {
               log.info("main-controller: " + e.getMessage());
            }
            if(member!=null){
                MemberDTO memberDTO = memberService.entityToMemberDTO(member);
                session.invalidate();
                session.setAttribute("member", memberDTO);
                log.info("member: " + memberDTO.toString());
            }
        }
        return "main/main";
    }
}
