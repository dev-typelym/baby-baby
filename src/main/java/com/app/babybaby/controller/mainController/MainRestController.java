package com.app.babybaby.controller.mainController;

import com.app.babybaby.domain.boardDTO.announcementDTO.AnnouncementDTO;
import com.app.babybaby.domain.boardDTO.askDTO.AskDTO;
import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.service.board.announcement.AnnouncementService;
import com.app.babybaby.service.board.ask.AskService;
import com.app.babybaby.service.board.event.EventService;
import com.app.babybaby.service.board.nowKids.NowKidsService;
import com.app.babybaby.service.board.parentsBoard.ParentsBoardService;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mains/*")
@RequiredArgsConstructor
@Slf4j
public class MainRestController {

    private final ParentsBoardService parentsBoardService;
    private final EventService eventService;
    private final NowKidsService nowKidsService;
    private final AnnouncementService announcementService;
    private final AskService askService;
    private final MemberService memberService;

    private final HttpSession session;

    @PostMapping("parentsBoards")
    public List<ParentsBoardDTO> getCategoryList() {
        List<ParentsBoard> categoryResults = parentsBoardService.find5RecentDesc();
        List<ParentsBoardDTO> dtos = categoryResults.stream().map(result -> parentsBoardService.toParentsBoardDTO(result)).collect(Collectors.toList());
        log.info("parentsBoards dtos: " + dtos.toString());
        return dtos;
    }

    @PostMapping("events")
    public List<EventDTO> getEventList(@RequestParam("category")String category) {
        log.info("===============category: "+category);
        List<Event> events = eventService.find5RecentDesc(CategoryType.valueOf(category));
        List<EventDTO> dtos = events.stream().map(result -> eventService.eventToDTO(result)).collect(Collectors.toList());
        log.info("events dtos: " + dtos.toString());
        return dtos;
    }

    @PostMapping("nowKids")
    public List<NowKidsDTO> getNowKidsList() {
        List<NowKids> nowKids = nowKidsService.find5RecentDesc();
        List<NowKidsDTO> dtos = nowKids.stream().map(result -> nowKidsService.toNowKidsDTOForMain(result)).collect(Collectors.toList());
        log.info("nowKids dtos: " + dtos.toString());
        return dtos;
    }

    @PostMapping("announcements")
    public List<AnnouncementDTO> getAnnouncementList() {
        List<Announcement> announcements = announcementService.find5RecentDesc();
        List<AnnouncementDTO> dtos = announcements.stream().map(result -> announcementService.toAnnouncementDTO(result)).collect(Collectors.toList());
        log.info("announcements dtos: " + dtos.toString());
        return dtos;
    }

    @PostMapping("asks")
    public AskDTO insertAskDTO(@RequestBody AskDTO askDTO) {
        log.info("================ASKDTO");
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        String memberEmail = memberDTO.getMemberEmail();
        askDTO.setMember(memberService.findByMemberEmail(memberEmail));
        log.info(askDTO.toString());

        askService.saveAskDTO(askDTO);

        return askDTO;
    }

}
