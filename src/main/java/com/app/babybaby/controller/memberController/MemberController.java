package com.app.babybaby.controller.memberController;

import com.app.babybaby.domain.memberDTO.CompanyDTO;
import com.app.babybaby.domain.memberDTO.MailDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.RandomKey;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.service.member.randomKey.RandomKeyService;
import com.app.babybaby.type.MemberType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final RandomKeyService randomKeyService;

    /* 회원 가입 페이지 이동*/
    @GetMapping("join/general")
    public String gotoJoinForm_general(MemberDTO memberDTO) {
        return "/member/join";
    }
    /* 회원 가입 페이지 이동*/
    @GetMapping("join/company")
    public String gotoJoinForm_company(MemberDTO memberDTO) {
        return "member/join-enterprise";
    }

    /* 회원가입후 login 페이지로 이동*/
    @PostMapping("join/general")
    public RedirectView join(MemberDTO memberDTO) {
        memberService.joinGeneral(memberDTO, passwordEncoder);
        return new RedirectView("/member/login");
    }

    /* 회원가입후 login 페이지로 이동*/
    @PostMapping("join/company")
    public RedirectView joinCompany(MemberDTO memberDTO) {
        memberService.joinCompany(memberDTO, passwordEncoder);
        return new RedirectView("/member/login");
    }

    /* 로그아웃 */
    @GetMapping("logout")
    public void goToLogout() {;}

    /* 비밀번호 찾기 페이지 */
    @GetMapping("find-password")
    public String findPassword() {
        return "/member/find-password";
    }

    /* 아이디 찾기 페이지 */
    @GetMapping("find-id")
    public String findId(){
        return "/member/find-id";
    }

//    /* 비밀번호 재설정 페이지 */
//    @GetMapping("changePW")
//    public String changePassword(){
//        return "/member/password-change";
//    }

    /* 로그인 페이지 */
    @GetMapping("login")
    public void goToLogin(){;}

    /* 비밀번호 변경페이지 */
    @GetMapping("change-password-request/{memberEmail}/{randomKey}")
    public String goChangePassword(@PathVariable("memberEmail") String memberEmail, @PathVariable("randomKey") String randomKey, Model model) {
        log.info("email: " + memberEmail);
        log.info("randomKey: " + randomKey);

        String latest = randomKeyService.getLatestRandomKey(memberService.findByMemberEmail(memberEmail).getId()).getRandomKey();
        log.info("latest: " + latest);

        if(latest.equals(randomKey)){
            model.addAttribute("memberEmail", memberEmail);
            model.addAttribute("result", true);
            model.addAttribute("errorMessage", "경로 인증에 성공 했습니다.");
        }
        else {
            model.addAttribute("result", false);
            model.addAttribute("errorMessage", "만료된 경로 입니다.");
        }
        log.info("model: " + model.getAttribute("errorMessage"));

        return "/member/password-change";
    }

    /* ****************************** 멤버 로그인 / 회원 가입 끝 *************************************************** */

    @GetMapping("details/{memberId}")
    public RedirectView goDetail(@PathVariable Long memberId){
        Member member = memberService.getMemberById(memberId).get();
        return member.getMemberType() == MemberType.COMPANY ?
                new RedirectView("/member/details/companies/" + memberId) : new RedirectView("/member/details/generals/" + memberId);
    }

    @GetMapping("/details/companies/{memberId}")
    public String goCompany(@PathVariable Long memberId){
        return "/member-detail/company-detail";
    }

    @GetMapping("/details/generals/{memberId}")
    public String goUser(@PathVariable Long memberId){
        return "/member-detail/member-detail";
    }

    @PostMapping("details/companies/{memberId}")
    @ResponseBody
    public CompanyDTO goCompanyRest(@PathVariable Long memberId){
        return memberService.getAllCompanyInfo(memberId);
    }

    @PostMapping("details/generals/{memberId}")
    @ResponseBody
    public MemberDTO goGeneralRest(@PathVariable Long memberId){
        return memberService.getAllUserInfo(memberId);
    }


}
