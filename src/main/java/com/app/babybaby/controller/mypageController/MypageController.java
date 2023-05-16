package com.app.babybaby.controller.mypageController;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.eventDTO.PageRequestDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.domain.purchaseDTO.purchaseDTO.PurchaseDTO;
import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.purchase.purchase.PurchaseRepository;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.service.board.parentsBoard.ParentsBoardService;
import com.app.babybaby.service.board.review.ReviewService;
import com.app.babybaby.service.like.eventLike.EventLikeService;
import com.app.babybaby.service.member.kid.KidService;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.service.purchase.coupon.CouponService;
import com.app.babybaby.service.purchase.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Autowired
    private final PurchaseService purchaseService;

    @Autowired
    private final EventLikeService eventLikeService;


//    캘린더 페이지
    @GetMapping("profile")
    public String getInfo(){
        return "myPage/myPage-profile";
    }




//    1:1 문의 목록
    @GetMapping("inquiry")
    public String getInquiry(){
        return "user-part/inquiry-list";
    }


//    통솔자 지원
    @GetMapping("crew")
    public String getCrew(){
        return "member/myPage-crew";
    }




//  쿠폰 페이지
    @GetMapping("coupon")
    public String getCoupon(Model model,@PageableDefault(size = 5)Pageable pageable,HttpSession httpSession){
        model.addAttribute("coupon",couponService.findCouponByMemberId(pageable,1L));
        return "myPage/myPage-coupon";
    }
    
//    결제 목록 페이지
    @GetMapping("payment")
    public String getPayment(Model model,HttpSession httpSession,@PageableDefault(size = 5)Pageable pageable){
        model.addAttribute("payment",purchaseService.findAllByMemberPaymentWithPage(pageable, 1L));
        return "myPage/myPage-payment";
    }

    @ResponseBody
    @PostMapping("payment")
    public Page<PurchaseDTO> getPayment(Pageable pageable){
       Page<PurchaseDTO> purchaseDTO = purchaseService.findAllByMemberPaymentWithPage(pageable, 1L);
        return purchaseDTO;
    }


//   리뷰 페이지
    @GetMapping("review")
    public String getReview(){
        return "myPage/myPage-review";
    }

    @ResponseBody
    @PostMapping("review/{page}")
    public Page<ReviewDTO> getReview(@PathVariable("page") Integer page){
        Page<ReviewDTO> reviewDTOS = reviewService.findReviewById(1L, PageRequest.of(page-1, 8));
        log.info(reviewDTOS.getContent().toString());
        return reviewDTOS;
    }

    //  내가좋아요한 이벤트게시글 페이지
    @GetMapping("play-like")
    public String getLike(){
        return "myPage/myPage-play-like";
    }

    @ResponseBody
    @PostMapping("play-like/{page}")
    public Slice<EventLikeDTO> getLike(Pageable pageable, @PathVariable(value = "page")Integer page){
        Slice<EventLikeDTO> eventLikeDTOS = eventLikeService.findEventLikeByMemberId(PageRequest.of(page, 12), 1L);
        log.info(eventLikeDTOS + "컨트롤러");
        return eventLikeDTOS;
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
