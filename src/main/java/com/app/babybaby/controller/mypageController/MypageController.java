package com.app.babybaby.controller.mypageController;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.service.board.parentsBoard.ParentsBoardService;
import com.app.babybaby.service.board.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final ParentsBoardService parentsBoardService;

    @GetMapping("review")
    public String getReview(Model model){
//        model.addAttribute("review",reviewService.);
        return "myPage/myPage-review";
    }

    @GetMapping("parent")
    public String getParent(Model model,@PageableDefault(size = 10) Pageable pageable){
        model.addAttribute("parent",parentsBoardService.getFindParentBoardListByMemberId(pageable,1L));
        return "myPage/myPage-parents-yards";
    }
}
