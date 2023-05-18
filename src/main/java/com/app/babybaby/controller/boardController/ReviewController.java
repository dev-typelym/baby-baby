package com.app.babybaby.controller.boardController;


import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.board.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/review/*")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("writeFirst")
    public String goReviewBoardWrite(){
        Long sessionId = 1L;

        return "review-board/review-board-write";
    }

    @GetMapping("writeSecond")
    public String goReviewBoardSecond(){
        return "review-board/review-board-thumbnail";
    }

    @PostMapping("save")
    public RedirectView saveReviewBoard(ReviewDTO reviewDTO, Long eventId){
        Long sessionId = 1L;
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
        Page<ReviewDTO> result = reviewService.getFindAllWithSearchParentsBoardList(
                PageRequest.of(page - 1, 10),
                parentsBoardSearch
        );
        return result;
    }



    @GetMapping("detail")
    public String goReviewDetail(){
        return "review-board/review-board-detail";
    }
}
