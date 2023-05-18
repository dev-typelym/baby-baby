package com.app.babybaby.controller.replyController;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.reply.parentsBoardReply.ParentsBoardReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parentsYard/reply/*")
@RequiredArgsConstructor
@Slf4j
public class ParentsBoardReplyController {

    @Autowired
    private final ParentsBoardReplyService parentsBoardReplyService;

//    @GetMapping("save/{parentsBoardId}")
//    @ResponseBody
//    public ParentsBoardReplyDTO saveParentsBoardReply(@PathVariable("parentsBoardId") Long parentsBoardId) {
//
//
//    }

    @GetMapping("list/show/{page}/{boardId}")
    @ResponseBody
    public Page<ParentsBoardReplyDTO> getParentsBoardReplyList(@PathVariable("page") Integer page,@PathVariable Long boardId) {
        log.info("asdsadsadsasadsadasdsd" + boardId);
        Page<ParentsBoardReplyDTO> parentsBoardReplyDTOS = parentsBoardReplyService.findAllReplyByBoardIdWithPaging(PageRequest.of(page, 3), boardId);
        return parentsBoardReplyDTOS;
    }

}
