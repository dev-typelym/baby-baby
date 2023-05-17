package com.app.babybaby.controller.memberController;

import com.app.babybaby.service.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/members/*")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    //   아이디 중복 검사
    @PostMapping("checkEmail")
    public Long checkId(@RequestParam("memberEmail") String memberEmail){
            log.info("memberEmail: " + memberEmail);
        return memberService.overlapByMemberEmail(memberEmail);
    }

    //  닉네임 중복 검사
    @PostMapping("checkNickname")
    public Long checkNickname(@RequestParam("memberNickname") String memberNickname){
        return memberService.overlapByMemberNickname(memberNickname);
    }

    //  휴대폰 번호 중복 검사
    @PostMapping("checkPhone")
    public Long checkPhone(@RequestParam("memberPhone") String memberPhone){
        return memberService.overlapByPhone(memberPhone);
    }

    @PostMapping("changePassword")
    public RedirectView changePassword(String memberPassword, String randomKey){

        return new RedirectView("/members/login");
    }

    @PostMapping("sendEmail")
    public Long sendEmailToFindPassword(@RequestParam("memberEmail") String memberEmail){
        return 1L;
    }


}