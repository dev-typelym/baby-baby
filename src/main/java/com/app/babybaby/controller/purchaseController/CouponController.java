package com.app.babybaby.controller.purchaseController;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.board.parentsBoard.ParentsBoardService;
import com.app.babybaby.service.purchase.coupon.CouponService;
import com.app.babybaby.service.reply.parentsBoardReply.ParentsBoardReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/coupon/*")
@RequiredArgsConstructor
@Slf4j
public class CouponController {
    private final CouponService couponService;
    private final HttpSession session;


    @GetMapping("parentsBoard")
    @ResponseBody
    public void getParentsBoardCoupon() {
    //    public void getParentsBoardCoupon(HttpSession httpSession) {
        //        Long memberId = (Long) httpSession.getAttribute("member").getId;
//        세션에서 받아오기 ID
//        couponService.saveCouponByParentsBoard(memberId);
        couponService.saveCouponByParentsBoard(1L);
    }

}
