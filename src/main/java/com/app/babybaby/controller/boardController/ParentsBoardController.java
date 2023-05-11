package com.app.babybaby.controller.boardController;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.boardService.parentsBoardService.ParentsBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/parentsYards/*")
@RequiredArgsConstructor
@Slf4j
public class ParentsBoardController {
    private final ParentsBoardService parentsBoardService;


    @GetMapping("list")
    public String goParentsBoards() {
        return "parents-yard-board/parents-yard-board";
    }

    //    pageableDefault는 몇개 뿌릴지를 저기서 정해주는 것이다.
    @GetMapping("list/show")
    @ResponseBody
    public Page<ParentsBoardDTO> getParentsBoards(@PageableDefault(page = 1,size = 10) Pageable pageable, ParentsBoardSearch parentsBoardSearch) {
        Page<ParentsBoardDTO> result = parentsBoardService.getFindAllWithSearchParentsBoardList(
                PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize())
                , parentsBoardSearch
        );
        return result;
    }

    @GetMapping("detail/{id}")
    public String goParentsBoardDetail(@PathVariable Long id, Model model) {
        model.addAttribute("parentsBoard", parentsBoardService.getParentsBoardDetail(id));
        return "parents-yard-board/parents-yard-board-detail";
    }

//    댓글 목록은 ajax로 만들어서 페이지 요청시에 바로 불러오자.

}