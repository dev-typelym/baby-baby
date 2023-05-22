package com.app.babybaby.controller.boardController;


import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.board.review.ReviewService;
import com.app.babybaby.type.CategoryType;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/review/*")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("writeFirst")
    public String goReviewBoardWrite(Model model){
        Long sessionId = 2L;
        List<EventDTO> events = reviewService.findAllEventsByMemberId(sessionId);

        Gson gson = new Gson();
        String eventsJson = gson.toJson(events);
        log.info(eventsJson);
        model.addAttribute("eventsJson", eventsJson);
        return "review-board/review-board-write";
    }

    @GetMapping("writeSecond")
    public String goReviewBoardSecond(Model model){
        Long sessionId = 2L;
        Member member = reviewService.getMemberInfo(sessionId);
//        로그인 되어있지 않을때 로그인 페이지로 보내주기
        model.addAttribute(member);

        return "review-board/review-board-thumbnail";
    }

    @PostMapping("save")
    public RedirectView saveReviewBoard(ReviewDTO reviewDTO, Long eventId){
        Long sessionId = 2L;
        log.info(reviewDTO.toString());
        reviewService.saveReview(sessionId, eventId, reviewDTO);
        return new RedirectView("/review/list");
    }

    @GetMapping("list")
    public String goListDetail(){
        return "review-board/review-board";
    }

    //    ajax로 불러온다 부모님 마당 게시글 목록
    //    pageableDefault는 몇개 뿌릴지를 저기서 정해주는 것이다.
    @GetMapping("list/show/{page}")
    @ResponseBody
    public Page<ReviewDTO> getParentsBoards(@PathVariable("page") Integer page, ParentsBoardSearch parentsBoardSearch) {
        log.info("================================" + parentsBoardSearch);
        Page<ReviewDTO> result = reviewService.getFindAllWithSearchReviewBoardList(
                PageRequest.of(page - 1, 10),
                parentsBoardSearch
        );
        return result;
    }


    //    부모님 마당 게시글 상세보기 (부모님 마당의 id를 가져와서 그 id를 통해 eventCategory를 가져온다.)
    @GetMapping("detail/{id}")
    public String goParentsBoardDetail(@PathVariable Long id, Model model) {
        model.addAttribute("parentsBoard", reviewService.getReviewBoardDetail(id));
        return "/parents-yard-board/parents-yard-board-detail";
    }

    //    상세보기 안에 카테고리 최신글 2개 가져오기
    @ResponseBody
    @PostMapping("detail/category/{boardId}")
    public List<ReviewDTO> getCategoryList(@PathVariable Long boardId) {
        CategoryType category = reviewService.findById(boardId).getEvent().getCategory();
        log.info("category: " + category.toString());
        List<ReviewDTO> categoryResults = reviewService.find2RecentDesc(category);
        log.info("categoryResults: " + categoryResults.toString());
        return categoryResults;
    }

    @GetMapping("detail")
    public String goReviewDetail(){
        return "review-board/review-board-detail";
    }
}
