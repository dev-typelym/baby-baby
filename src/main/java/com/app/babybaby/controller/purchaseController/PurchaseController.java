package com.app.babybaby.controller.purchaseController;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.service.purchase.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment/*")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("pay/{eventId}")
    public String goPurchase(@PathVariable Long eventId, Model model){
        Long sessionId = 2L;
        EventDTO eventDTO = purchaseService.findAllInfoForPayment(sessionId, eventId);
        model.addAttribute("eventDTO", eventDTO);
        return "payment/payment";
    }
}
