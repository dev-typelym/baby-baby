package com.app.babybaby.controller.mypageController;

import com.app.babybaby.domain.boardDTO.askDTO.AskDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.domain.purchaseDTO.purchaseDTO.PurchaseDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.search.admin.AdminAskSearch;
import com.app.babybaby.service.board.ask.AskService;
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
import org.springframework.security.core.parameters.P;
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

    @Autowired
    private final AskService askService;






//    캘린더 페이지
    @GetMapping("profile")
    public String getPofile(){
        return "myPage/myPage-profile";
    }


//    회원정보수정페이지
    @GetMapping("info")
    public String getInfo(){
        return "myPage/myPage-info";
    }


//    1:1 문의 목록
    @GetMapping("inquiry")
    public String getInquiry(){
        return "user-part/inquiry-list";
    }

    @ResponseBody
    @PostMapping("inquiry/{page}&&{boardTitle}")
    public Slice<AskDTO> getInquiry(@PathVariable(value = "page") Integer page,HttpSession httpSession,@PathVariable(value = "boardTitle") String boardTitle){
        log.info("@@@@@@@@@여기에요!!여기!!@@@@@@@@");
        AdminAskSearch adminAskSearch = new AdminAskSearch();
        adminAskSearch.setAskTitle(boardTitle);
        Slice<AskDTO> AskDTOS = askService.findAllAskByMemberId(1L,PageRequest.of(page, 10),adminAskSearch);
        log.info(page + "1111111111111111111");
        log.info(AskDTOS + "컨트롤러 들어옴");
        return AskDTOS;
    }


//    통솔자 지원
    @GetMapping("crew")
    public String getCrew(){
        return "myPage/myPage-crew";
    }




//  쿠폰 페이지
    @GetMapping("coupon")
    public String getCoupon(Model model,@PageableDefault(size = 5)Pageable pageable,HttpSession httpSession){
        model.addAttribute("coupon",couponService.findCouponByMemberId(pageable,1L));
        return "myPage/myPage-coupon";
    }

//  결제 상세 페이지
    @GetMapping("payment/detail/{purchaseId}")
    public String getPaymentDetail(Model model,@PathVariable(value = "purchaseId") Long purchaseId){
        model.addAttribute("paymentDetail",purchaseService.findMemberIdByPaymentDetail(purchaseId));
        log.info(purchaseId.toString());
        return "myPage/payment-detail";
    }
    
//    결제 목록 페이지
    @GetMapping("payment")
    public String getPayment(Model model){
        return "myPage/myPage-payment";
    }


    @ResponseBody
    @PostMapping("payment/{page}")
    public Page<PurchaseDTO> getPayment(@PathVariable(value = "page") Integer page){
       Page<PurchaseDTO> purchaseDTOS = purchaseService.findAllByMemberPaymentWithPage(PageRequest.of(page, 10), 1L);
        log.info(page + "1111111111111111111");
        log.info(purchaseDTOS + "컨트롤러 들어옴");
        return purchaseDTOS;
    }


//   리뷰 페이지
    @GetMapping("review")
    public String getReview(){
        return "myPage/myPage-review";
    }

    @ResponseBody
    @PostMapping("review/{page}")
    public Page<ReviewDTO> getReview(@PathVariable("page") Integer page){
        Page<ReviewDTO> reviewDTOS = reviewService.findReviewById(1L, PageRequest.of(page, 8));
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
//        model.addAttribute("parent",parentsBoardService.getFindParentBoardListByMemberId(pageable,1L));
        return "myPage/myPage-parents-yards";
    }

    @PostMapping("parent/{page}")
    @ResponseBody
    public Page<ParentsBoardDTO> getParent(@PathVariable(value = "page") Integer page){
        Page<ParentsBoardDTO> parentsBoardDTOS = parentsBoardService.getFindParentBoardListByMemberId(PageRequest.of(page, 10), 1L);
        return parentsBoardDTOS;
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
