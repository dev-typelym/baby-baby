package com.app.babybaby.controller.purchaseController;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.domain.purchaseDTO.purchaseDTO.PurchaseDTO;
import com.app.babybaby.service.purchase.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment/*")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("pay/{eventId}")
    public String goPurchase(@PathVariable("eventId") Long eventId,HttpSession session, Model model, HttpServletRequest request){
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

        String eventRegisterDate = request.getParameter("eventRegisterDate");
        log.info("eventRegisterDate은 : " + eventRegisterDate);
        model.addAttribute("eventRegisterDate", eventRegisterDate);
        model.addAttribute("kidIdList", kidIdList);
        return "payment/payment";
    }

    @GetMapping("save")
    @ResponseBody
    public Long pay(Long eventId, PurchaseDTO purchaseDTO, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        Long sessionId = memberDTO.getId();

        List<KidDTO> kids = new ArrayList<>();
        for (Long kidId : purchaseDTO.getKidList()) {
            KidDTO kidDTO = new KidDTO();
            kidDTO.setId(kidId);
            kids.add(kidDTO);
        }
        purchaseDTO.setKids(kids);
        log.info(purchaseDTO.getEventRegisterDate());
        log.info(purchaseDTO.toString(), "==============={} 제발");
         purchaseService.saveAll(sessionId, eventId, purchaseDTO);

        return 1L;
    }
}
