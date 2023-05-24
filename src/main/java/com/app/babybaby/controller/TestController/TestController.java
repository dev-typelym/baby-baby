package com.app.babybaby.controller.TestController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/main/main")
    public void main(){}

//    @GetMapping("/member/find-id")
//    public void findId(){}
//
//    @GetMapping("/member/find-password")
//    public void findPassword(){}
//
//    @GetMapping("/member/join")
//    public void join(){}
//
//    @GetMapping("/member/join-enterprise")
//    public void joinEnterprise(){}
//
//    @GetMapping("/member/login")
//    public void login(){}
//
//    @GetMapping("/member/myPage-crew")
//    public void myPageCrew(){}
//
//    @GetMapping("/member/password-change")
//    public void passwordChange(){}

    @GetMapping("/member-detail/member-detail")
    public void memberDetail(){}

    @GetMapping("/member-detail/company-detail")
    public void companyDetail(){}

    @GetMapping("/myPage/myPage-main-kdh")
    public void myPageMain(){}

    @GetMapping("/myPage/children-register")
    public void childrenRegister(){}

    @GetMapping("/myPage/myPage-alarm")
    public void alarm(){}

    @GetMapping("/myPage/myPage-coupon")
    public void coupon(){}

    @GetMapping("/myPage/myPage-follow")
    public void follow(){}

    @GetMapping("/myPage/myPage-info")
    public void info(){}

    @GetMapping("/myPage/myPage-ourKids-like")
    public void ourKidsLike(){}

    @GetMapping("/myPage/myPage-parents-yards")
    public void yards(){}

    @GetMapping("/myPage/myPage-payment")
    public void myPagePayment(){}

    @GetMapping("/myPage/myPage-play-like")
    public void playLike(){}

    @GetMapping("/myPage/myPage-profile")
    public void profile(){}

    @GetMapping("/myPage/myPage-review")
    public void review(){}

    @GetMapping("/myPage/myPage-withDrawl")
    public void withDrawl(){}

    @GetMapping("/myPage/myPage-following")
    public void following(){}

    @GetMapping("/myPage/payment-detail")
    public void paymentDetail(){}

    @GetMapping("/parents-yard-board/parents-yard-board")
    public void parentsYardBoard(){}

    @GetMapping("/parents-yard-board/parents-yard-board-detail")
    public void parentsYardBoardDetail(){}

    @GetMapping("/parents-yard-board/parents-yard-board-thumbnail")
    public void parentsYardBoardThumbnail(){}

    @GetMapping("/parents-yard-board/parents-yard-board-write")
    public void parentsYardBoardWrite(){}

    @GetMapping("/payment/payment")
    public void payment(){}

    @GetMapping("/play/event-board-detail")
    public void eventBoardList(){}

    @GetMapping("/play/event-category-list")
    public void eventCategoryList(){}

    @GetMapping("/play/event-join-write")
    public void eventJoinWrite(){}

    @GetMapping("/play/play-write-field")
    public void playWriteField(){}

    @GetMapping("/play/play-write-multi")
    public void playWriteMulti(){}

    @GetMapping("/play/play-write-single")
    public void playWriteSingle(){}

    @GetMapping("/review-board/review-board")
    public void reviewBoard(){}

    @GetMapping("/review-board/review-board-detail")
    public void reviewBoardDetail(){}

    @GetMapping("/review-board/review-board-thumbnail")
    public void reviewBoardThumbnail(){}

    @GetMapping("/review-board/review-board-write")
    public void reviewBoardWrite(){}

    @GetMapping("/user-part/announcement")
    public void announcement(){}

    @GetMapping("/user-part/inquiry-list")
    public void inquiry(){}

    @GetMapping("/admin/admin-announcementList")
    public void adminAnnouncementList(){}

    @GetMapping("/admin/admin-askList")
    public void askList(){}

    @GetMapping("/admin/admin-companyList")
    public void companyList(){}

    @GetMapping("/admin/admin-eventList")
    public void eventList(){}

    @GetMapping("/admin/admin-guideList")
    public void guideList(){}

    @GetMapping("/admin/admin-memberList")
    public void memberList(){}

    @GetMapping("/admin/admin-nowKidsList")
    public void nowKidsList(){}

    @GetMapping("/admin/admin-parentsBoardList")
    public void parentsBoardList(){}

    @GetMapping("/admin/admin-parentsBoardReplyList")
    public void parentsBoardReplyList(){}

    @GetMapping("/admin/admin-reviewList")
    public void reviewList(){}

    @GetMapping("/admin/admin-reviewReplyList")
    public void reviewReplyList(){}

    @GetMapping("/myPage/children-register-clone")
    public void pageClone(){}

    @GetMapping("/error/service-preparing")
    public void servocePreparing(){}

}
