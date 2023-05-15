package com.app.babybaby.controller.mypageController;

import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.service.board.parentsBoard.ParentsBoardService;
import com.app.babybaby.service.board.review.ReviewService;
import com.app.babybaby.service.member.kid.KidService;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.service.purchase.coupon.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final ParentsBoardService parentsBoardService;
    @Autowired
    private final KidService kidService;
    @Autowired
    private final MemberService memberService;
    @Autowired
    private final CouponService couponService;


    @GetMapping("coupon")
    public String getCoupon(Model model,@PageableDefault(size = 5)Pageable pageable,HttpSession httpSession){
        model.addAttribute("coupon",couponService.findCouponByMemberId(pageable,1L));
        return "myPage/myPage-coupon";
    }



//   리뷰 페이지
    @GetMapping("review")
    public String getReview(Model model){
//        model.addAttribute("review",reviewService.);
        return "myPage/myPage-review";
    }



//    내가쓴 부모님 마당
    @GetMapping("parent")
    public String getParent(Model model,@PageableDefault(size = 10) Pageable pageable){
        model.addAttribute("parent",parentsBoardService.getFindParentBoardListByMemberId(pageable,1L));
        return "myPage/myPage-parents-yards";
    }


//    아이등록 페이지
    @GetMapping("children-register")
    public String getChildren(Model model, HttpSession httpSession){
        httpSession.setAttribute("memberId", 1L);
        model.addAttribute(new Kid());
        return "myPage/children-register-clone";
    }


    @PostMapping("children-register")
    public RedirectView getChildren(HttpSession httpSession, KidDTO kidDTO,Kid kid, RedirectAttributes redirectAttributes){
        Long memberId = (Long) httpSession.getAttribute("memberId");
//        memberService.getMemberById(memberId).ifPresent(member -> kidDTO.setParent(member));
        log.info(kidDTO.toString());
        kidService.save(kidDTO);
        return new RedirectView("parent"); // <<<< 경로 수정할것
    }


}
