package com.app.babybaby.controller.boardController;


import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.service.board.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/events/*")
@RequiredArgsConstructor
public class EventRestController {
    private final EventService eventService;

    @PostMapping("details")
    public Boolean getAllEvents(Long eventId, HttpSession session){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Long sessionId = memberDTO.getId();
        return null;
    }
}
