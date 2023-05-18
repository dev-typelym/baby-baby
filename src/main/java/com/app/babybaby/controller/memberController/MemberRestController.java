package com.app.babybaby.controller.memberController;

import com.app.babybaby.domain.memberDTO.MailDTO;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.service.member.randomKey.RandomKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/members/*")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;
    private final RandomKeyService randomKeyService;


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

    /* 이메일 보내기 */
    @PostMapping("sendEmail")
    @Transactional(rollbackFor = Exception.class)
    public Long sendEmailToFindPassword(@RequestParam("memberEmail") String memberEmail){

            Member member = memberService.findByMemberEmail(memberEmail);

            String randomKey = randomKeyService.saveRandomKey(member).getRandomKey();

            MailDTO mailDTO = new MailDTO();
            mailDTO.setAddress(memberEmail);
            mailDTO.setTitle("[아기자기]새 비밀번호 설정 링크입니다.");
            mailDTO.setMessage("링크: http://localhost:10000/member/change-password?memberEmail=" + memberEmail + "&randomKey=" + randomKey);
            memberService.sendMail(mailDTO);

            return 1L;
    }


}