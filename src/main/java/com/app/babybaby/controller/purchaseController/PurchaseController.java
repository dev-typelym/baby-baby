package com.app.babybaby.controller.purchaseController;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.service.purchase.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment/*")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("pay/{eventId}")
    public String goPurchase(@PathVariable Long eventId,HttpSession session, Model model, HttpServletRequest request){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Long sessionId = memberDTO.getId();
        EventDTO eventDTO = purchaseService.findAllInfoForPayment(sessionId, eventId);
        model.addAttribute("eventDTO", eventDTO);
        model.addAttribute("eventId", eventId);


        String[] kidIds = request.getParameterValues("kidIds");
        List<Long> kidIdList = new ArrayList<>();
        if (kidIds != null) {
            for (String kidId : kidIds) {
                kidIdList.add(Long.parseLong(kidId));
            }
        }
        model.addAttribute("kidIds", kidIdList);
        return "payment/payment";
    }
}
