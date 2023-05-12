package com.app.babybaby.controller.boardController;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.board.parentsBoard.ParentsBoard;
import com.app.babybaby.service.reply.parentsBoardReply.ParentsBoardReply;
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

@Controller
@RequestMapping("/parentsYard/*")
@RequiredArgsConstructor
@Slf4j
public class ParentsBoardController {
    private final ParentsBoard parentsBoard;
    private final ParentsBoardReply parentsBoardReply;


    //    첫 게시글 목록 화면으로 가기
    @GetMapping("list")
    public String goParentsBoards() {
        return "parents-yard-board/parents-yard-board";
    }

    //    ajax로 불러온다 부모님 마당 게시글 목록
    //    pageableDefault는 몇개 뿌릴지를 저기서 정해주는 것이다.
    @GetMapping("list/show")
    @ResponseBody
    public Page<ParentsBoardDTO> getParentsBoards(@PageableDefault(page = 1,size = 10) Pageable pageable, ParentsBoardSearch parentsBoardSearch) {
        Page<ParentsBoardDTO> result = parentsBoard.getFindAllWithSearchParentsBoardList(
                PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize())
                , parentsBoardSearch
        );
        return result;
    }

    //    부모님 마당 게시글 상세보기
    @GetMapping("detail/{id}")
    public String goParentsBoardDetail(@PathVariable Long id, Model model) {
        model.addAttribute("parentsBoard", parentsBoard.getParentsBoardDetail(id));
        return "parents-yard-board/parents-yard-board-detail";
    }

    //    댓글 목록은 ajax로 만들어서 페이지 요청시에 바로 불러오자.
    @GetMapping("detail/reply/{id}")
    @ResponseBody
    public Slice<ParentsBoardReplyDTO> getParentsBoardReplies(@PageableDefault(page = 1,size = 5) Pageable pageable, @PathVariable Long id) {
        return parentsBoardReply.findAllReplyByBoardIdWithPaging(
                PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize()), id
        );
    }

}