package com.app.babybaby.controller.memberController;

import com.app.babybaby.domain.memberDTO.CompanyDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.service.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

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

    /* 비밀번호 재설정 페이지 */
    @GetMapping("password-change")
    public String changePassword(){
        return "/member/password-change";
    }

    /* 로그인 페이지 */
    @GetMapping("login")
    public void goToLogin(){;}

//    /* 카카오 회원가입 */
//    @GetMapping("kakao")
//    public RedirectView kakaoJoin(String code, RedirectAttributes redirectAttributes) throws Exception {
//        String token = memberService.getKaKaoAccessToken(code, "join");
//        MemberDTO kakaoInfo = memberService.getKakaoInfo(token);
//
//        kakaoInfo.setMemberJoinType(MemberJoinType.카카오);
//
//        MemberDTO memberDTO = memberService.getMemberByEmail(kakaoInfo.getMemberEmail());
//
//        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
//        if (memberDTO == null || memberDTO.getMemberJoinType() != MemberJoinType.카카오) {
//            redirectAttributes.addFlashAttribute("kakaoInfo", kakaoInfo.getMemberEmail());
//            return new RedirectView("/member/join");
//        }
//
//        return new RedirectView("/member/join-select?join=false");
//    }
//
//    /* 카카오 로그인 */
//    @GetMapping("kakao-login")
//    public RedirectView kakaoLogin(String code) throws Exception {
//        /*String userIdentification = null;*/
//
//        String token = memberService.getKaKaoAccessToken(code, "login");
//        memberService.getKakaoInfo(token);
//
//        MemberDTO kakaoInfo = memberService.getKakaoInfo(token);
//        MemberDTO memberDTO = memberService.getMemberByEmail(kakaoInfo.getMemberEmail());
//
//        if(memberDTO == null || memberDTO.getMemberJoinType() != MemberJoinType.카카오){
//            return new RedirectView("/member/login?check=false");
//        }
//
//        /*session.setAttribute("user", userVO);*/
//        return new RedirectView("/main/");
//    }

    //    [회원 상세] 회원 상세 페이지가지
    @GetMapping("details/{id}")
    public String goDetails(@PathVariable Long id){
        return "/member-detail/company-detail";
    }
    
//    [회원 상세] 회사 정보 가져오기
    @PostMapping("details/{id}")
    @ResponseBody
    public CompanyDTO getMemberInfoForDetail(@PathVariable Long id){
        return memberService.getAllMemberInfo(id);
    }

}
