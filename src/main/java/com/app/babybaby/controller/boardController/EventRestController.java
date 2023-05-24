package com.app.babybaby.controller.boardController;


import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.service.board.event.EventService;
import com.app.babybaby.service.member.guide.GuideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/events/*")
@RequiredArgsConstructor
public class EventRestController {
    private final EventService eventService;

    private final GuideService guideService;

    @PostMapping("details")
    public Boolean getAllEvents(Long eventId, HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Long sessionId = memberDTO.getId();
        return null;
    }

    @PostMapping("applyGuide")
    @ResponseBody
    public Boolean applyGuide(Long eventId, HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        if(memberDTO != null){
            Long sessionId = memberDTO.getId();
            guideService.applyGuide(sessionId, eventId);
            return true;
        }
        log.info(eventId.toString());
        return false;
    }
}
