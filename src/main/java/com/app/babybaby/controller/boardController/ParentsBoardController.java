package com.app.babybaby.controller.boardController;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.entity.reply.parentsBoardReply.QParentsBoardReply;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.board.event.EventService;
import com.app.babybaby.service.board.parentsBoard.ParentsBoardService;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.service.reply.parentsBoardReply.ParentsBoardReplyService;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/parentsYard/*")
@RequiredArgsConstructor
@Slf4j
public class ParentsBoardController {
    private final ParentsBoardService parentsBoardService;
    private final ParentsBoardReplyService parentsBoardReplyService;
    private final EventService eventService;
    private final HttpSession session;
    private final MemberService memberService;

    private Long getMemberIdByEmail(String memberEmail){
        return memberService.findByMemberEmail(memberEmail).getId();
    }

    private Long getSessionMemberId(){
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        return memberDTO.getId();
    }


    //    첫 게시글 목록 화면으로 가기
    @GetMapping("list")
    public String goParentsBoards() {
        return "parents-yard-board/parents-yard-board";
    }


    //    ajax로 불러온다 부모님 마당 게시글 목록
    //    pageableDefault는 몇개 뿌릴지를 저기서 정해주는 것이다.
    @GetMapping("list/show/{page}")
    @ResponseBody
    public Page<ParentsBoardDTO> getParentsBoards(@PathVariable("page") Integer page, ParentsBoardSearch parentsBoardSearch) {
        log.info("================================" + parentsBoardSearch);
        Page<ParentsBoardDTO> result = parentsBoardService.getFindAllWithSearchParentsBoardList(
                PageRequest.of(page - 1, 10),
                parentsBoardSearch
        );
        return result;
    }


    //    부모님 마당 게시글 상세보기 (부모님 마당의 id를 가져와서 그 id를 통해 eventCategory를 가져온다.)
    @GetMapping("detail/{id}")
    public String goParentsBoardDetail(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Long sessionId = memberDTO.getId();
        model.addAttribute("parentsBoard", parentsBoardService.getParentsBoardDetail(id));
        model.addAttribute("member", parentsBoardService.getParentsBoardDetail(id));
        log.info("페어런트 보드입니다 : " + parentsBoardService.getParentsBoardDetail(id).toString());
        return "/parents-yard-board/parents-yard-board-detail";
    }

//    상세보기 안에 카테고리 최신글 2개 가져오기
    @ResponseBody
    @PostMapping("detail/category/{boardId}")
    public List<ParentsBoardDTO> getCategoryList(@PathVariable Long boardId) {
        CategoryType category = parentsBoardService.findById(boardId).getEvent().getCategory();
        log.info("category: " + category.toString());
        List<ParentsBoardDTO> categoryResults = parentsBoardService.find2RecentDesc(category);
        log.info("categoryResults: " + categoryResults.toString());
        return categoryResults;
    }


//    댓글 저장
//    @GetMapping("detail/replySave/{id}")
//    @ResponseBody
//    public String saveRely(ParentsBoardReply parentsBoardReply) {
//        return parentsBoardReplyService.save(parentsBoardReply);
//    }

    @GetMapping("writeFirst")
    public String getParentsBoardFirst(Model model) {

        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        Long sessionId = memberDTO.getId();
        List<EventDTO> eventDTOS = parentsBoardService.getFindByEventId(sessionId).stream().map(eventService::eventToDTO).collect(Collectors.toList());
        Member member = parentsBoardService.getUserInfo(sessionId);
        log.info(parentsBoardService.getFindByEventId(sessionId) + "이벤트아이디 0임?");
        model.addAttribute("eventDTOS", eventDTOS);
        model.addAttribute("eventDTO", parentsBoardService.getFindByEventId(sessionId));
        model.addAttribute("memberInfo", member);
        return "parents-yard-board/parents-yard-board-write";
    }

//    대표사진 올리는 페이지 불러오기
    @GetMapping("writeSecond")
    public String getParentsBoardWrite(Model model) {
        Long sessionId = getSessionMemberId();
        Member member = parentsBoardService.getUserInfo(sessionId);
        model.addAttribute("memberInfo", member);
        return "parents-yard-board/parents-yard-board-thumbnail";
    }

//    모든 정보 저장
    @GetMapping
    public RedirectView saveALl(ParentsBoardDTO parentsBoardDTO){
        Long sessionId = getSessionMemberId();
        parentsBoardService.saveAll(sessionId, parentsBoardDTO.getEventId(), parentsBoardDTO);
        log.info("parentBoard를 save하기 위한 곳 : " + parentsBoardDTO.toString());
        return new RedirectView("/parentsYard/list");
    }

//    대표사진 올리는 페이지 ajax를 위한 컨트롤러
    @PostMapping("writeSecond")
    public String getParentsBoardWritePost(ParentsBoardDTO parentsBoardDTO) {
        log.info("============================================" + parentsBoardDTO);
        parentsBoardService.save(parentsBoardDTO);
        return "parents-yard-board/parents-yard-board-thumbnail";
    }

}