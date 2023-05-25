package com.app.babybaby.controller.mypageController;

import com.app.babybaby.domain.boardDTO.askDTO.AskDTO;
import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.domain.likeDTO.nowKidsLikeDTO.NowKidsLikeDTO;
import com.app.babybaby.domain.memberDTO.EventKidDTO;
import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.domain.purchaseDTO.purchaseDTO.PurchaseDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.like.nowKidsLike.NowKidsLikeRepository;
import com.app.babybaby.search.admin.AdminAskSearch;
import com.app.babybaby.service.board.ask.AskService;
import com.app.babybaby.service.board.event.EventService;
import com.app.babybaby.service.board.parentsBoard.ParentsBoardService;
import com.app.babybaby.service.board.review.ReviewService;
import com.app.babybaby.service.like.eventLike.EventLikeService;
import com.app.babybaby.service.like.nowKidsLike.NowKidsLikeService;
import com.app.babybaby.service.member.crew.CrewService;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    private final ReviewService reviewService;
    private final ParentsBoardService parentsBoardService;
    private final KidService kidService;
    private final MemberService memberService;
    private final CouponService couponService;
    private final PurchaseService purchaseService;
    private final EventLikeService eventLikeService;
    private final AskService askService;
    private final NowKidsLikeService nowKidsLikeService;
    private final EventService eventService;
    private final PasswordEncoder passwordEncoder;
    private final CrewService crewService;
    private final HttpSession session;

    private Long getMemberIdByEmail(String memberEmail){
        return memberService.findByMemberEmail(memberEmail).getId();
    }

    private Long getSessionMemberId(){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        return memberDTO.getId();
    }


    //    마이페이지 메인
    @GetMapping("main")
    public String getMain(Model model,HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        model.addAttribute("purchaseCount",purchaseService.findMemberByIdWithCount(memberDTO.getId()));
        model.addAttribute("kid",kidService.findALlMyKid(memberDTO.getId()));
        model.addAttribute("kidCount",kidService.findALlMyKidCount(memberDTO.getId()));
        model.addAttribute("member",memberService.findByMemberId(memberDTO.getId()));
        model.addAttribute("couponCount",couponService.totalCouponCount(memberDTO.getId()));
        return "myPage/myPage-main-kdh";
    }


//    내가 올린 이벤트 게시글
    @GetMapping("my-event")
    public String getMyEvent(){
        return "myPage/myPage-my-event";
    }

    @PostMapping("my-event/{page}")
    @ResponseBody
    public Slice<EventDTO> getMyEvent(@PathVariable(value = "page") Integer page,HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        log.info("dksdsadasdasd");
        Slice<EventDTO> eventDTOS = eventService.findMemberIdByEventListWithPaging(memberDTO.getId(),PageRequest.of(page, 5));
        log.info(eventDTOS.toString());

        return eventDTOS;
    }



//    캘린더 페이지
    @GetMapping("profile")
    public String getPofile(){
        return "myPage/myPage-profile";
    }

    @ResponseBody
    @PostMapping("profile/{date}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public List<EventKidDTO> getPofile(Model model, @PathVariable("date") String date1){
        LocalDate date = LocalDate.parse(date1);
        log.info("------------------- 받아온 date : "+ date);
        List<EventKidDTO> eventKidDTOS = crewService.findCrewByMemberId(getSessionMemberId(), date);
        model.addAttribute("eventKidDTOS",eventKidDTOS);
        log.info(eventKidDTOS.toString());
        return eventKidDTOS;
    }


//    회원정보수정페이지
    @GetMapping("info")
    public String getInfo(Model model,HttpSession session){
        MemberDTO member = (MemberDTO)session.getAttribute("member");
        log.info(member.toString());
//       memberService.getMemberById(memberId).ifPresent(member -> model.addAttribute("memberDTO", memberService.findByMemberId(memberId)));
        model.addAttribute("memberDTO", memberService.findByMemberId(member.getId()));
//        log.info(memberService.findByMemberId(member.getId()) + "@@@@이겅미");
        return "myPage/myPage-info";
    }

    @PostMapping("info")
    @ResponseBody
    public MemberDTO getInfo(MemberDTO memberDTO, HttpSession session){
        log.info(memberDTO.toString() + "<- 화면에서 받아온 값");
        memberService.setInfoMemberById(memberDTO,passwordEncoder);
        log.info(memberDTO.toString() + "이건 밑에11111");

        return memberService.findByMemberId(memberDTO.getId());
    }

    @PostMapping("info-password")
    public RedirectView getPassword(HttpSession session, @RequestParam("memberPassword") String memberPassword, PasswordEncoder passwordEncoder){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        memberService.updatePassword(memberDTO.getId(),memberPassword,passwordEncoder);
        return new RedirectView("info");
    }

//    1:1 문의 목록
    @GetMapping("inquiry")
    public String getInquiry(){
        return "user-part/inquiry-list";
    }

    @ResponseBody
    @PostMapping("inquiry/{page}&&{boardTitle}")
    public Slice<AskDTO> getInquiry(@PathVariable(value = "page") Integer page,HttpSession session,@PathVariable(value = "boardTitle") String boardTitle){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");

        log.info("@@@@@@@@@여기에요!!여기!!@@@@@@@@");
        AdminAskSearch adminAskSearch = new AdminAskSearch();
        adminAskSearch.setAskTitle(boardTitle);
        Slice<AskDTO> AskDTOS = askService.findAllAskByMemberId(memberDTO.getId(),PageRequest.of(page, 10),adminAskSearch);
        log.info(page + "1111111111111111111");
        log.info(AskDTOS + "컨트롤러 들어옴");
        return AskDTOS;
    }





//  쿠폰 페이지
    @GetMapping("coupon")
    public String getCoupon(Model model,@PageableDefault(size = 5)Pageable pageable,HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        model.addAttribute("coupon",couponService.findCouponByMemberId(pageable,memberDTO.getId()));
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
    public Page<PurchaseDTO> getPayment(@PathVariable(value = "page") Integer page,HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
       Page<PurchaseDTO> purchaseDTOS = purchaseService.findAllByMemberPaymentWithPage(PageRequest.of(page, 10), memberDTO.getId());
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
    public Page<ReviewDTO> getReview(@PathVariable("page") Integer page, Pageable Pageable,HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Page<ReviewDTO> reviewDTOS = reviewService.findReviewById(memberDTO.getId(),PageRequest.of(page, 5));
        log.info(page + "페이지들어몸");
        log.info(reviewDTOS.getContent().toString() + "컨트롤러");
        return reviewDTOS;
    }

//  내가좋아요한 이벤트게시글 페이지
    @GetMapping("play-like")
    public String getLike(){
        return "myPage/myPage-play-like";
    }

    @ResponseBody
    @PostMapping("play-like/{page}")
    public Slice<EventLikeDTO> getLike(Pageable pageable, @PathVariable(value = "page")Integer page,HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Slice<EventLikeDTO> eventLikeDTOS = eventLikeService.findEventLikeByMemberId(PageRequest.of(page, 6), memberDTO.getId());
        log.info(eventLikeDTOS + "컨트롤러");
        return eventLikeDTOS;
    }

    @PostMapping("delete/{eventId}")
    @ResponseBody
    public void likeDelete(@PathVariable Long eventId, HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        eventLikeService.deleteLike(eventId, memberDTO.getId());
    }



//    내가쓴 부모님 마당
    @GetMapping("parent")
    public String getParent(Model model,@PageableDefault(size = 10) Pageable pageable){
//        model.addAttribute("parent",parentsBoardService.getFindParentBoardListByMemberId(pageable,1L));
        return "myPage/myPage-parents-yards";
    }

    @PostMapping("parent/{page}")
    @ResponseBody
    public Page<ParentsBoardDTO> getParent(@PathVariable(value = "page") Integer page,HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Page<ParentsBoardDTO> parentsBoardDTOS = parentsBoardService.getFindParentBoardListByMemberId(PageRequest.of(page, 5), memberDTO.getId());
        return parentsBoardDTOS;
    }


    //    통솔자 지원
    @GetMapping("crew")
    public String getCrew(){
        return "myPage/myPage-crew";
    }

    @PostMapping("crew")
    public RedirectView getCrew(MemberDTO memberDTO,HttpSession session){
        MemberDTO memberDTO1 = (MemberDTO)session.getAttribute("member");
        memberService.save(memberDTO,memberDTO1.getId());
        log.info(memberDTO.toString());
        return new RedirectView("profile");
    }

//    아이등록 페이지
    @GetMapping("kid/register")
    public String getChildren(Model model, HttpSession session ){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        model.addAttribute(new Kid());
        return "myPage/children-register-clone";
    }


    @PostMapping("kid/register")
    public RedirectView getChildren(HttpSession session, KidDTO kidDTO,Kid kid, RedirectAttributes redirectAttributes){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        memberService.getMemberById(memberDTO.getId()).ifPresent(member -> kidDTO.setParent(member));
        log.info(kidDTO.toString());
        kidService.save(kidDTO);
        return new RedirectView("/mypage/main"); // <<<< 경로 수정할것
    }



    //    지금우리 아이들은 좋아요 목록
    @GetMapping("nowkid")
    public String getNowKid(){
        return "myPage/myPage-ourKids-like";
    }

    @PostMapping("nowkid/{page}")
    @ResponseBody
    public Slice<NowKidsLikeDTO> getNowKid(@PathVariable Integer page,HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Slice<NowKidsLikeDTO> nowKidsLikeDTOS = nowKidsLikeService.findEventLikeByMemberId(PageRequest.of(page, 12), memberDTO.getId());
        log.info(nowKidsLikeDTOS + "컨트롤러");
        return nowKidsLikeDTOS;
    }




    @GetMapping("following")
    public String goFollowing(){
        return "myPage/myPage-following";
    }


    @GetMapping("follower")
    public String goFollower(){
        return "myPage/myPage-follow";
    }




}
