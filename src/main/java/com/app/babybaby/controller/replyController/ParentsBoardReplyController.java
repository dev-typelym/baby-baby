package com.app.babybaby.controller.replyController;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.service.reply.parentsBoardReply.ParentsBoardReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/parentsYard/reply/*")
@RequiredArgsConstructor
@Slf4j
public class ParentsBoardReplyController {

    private final ParentsBoardReplyService parentsBoardReplyService;

    // 댓글작성
    @GetMapping("write/{parentsBoardId}")
    @ResponseBody
    public ParentsBoardReplyDTO saveParentsBoardReply(ParentsBoardReplyDTO parentsBoardReplyDTO, @PathVariable("parentsBoardId") Long parentsBoardId, HttpSession httpSession) {
//        세션에서 받아오기 ID
        MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
        Long memberId = memberDTO.getId();
        parentsBoardReplyDTO.setParentsBoardId(parentsBoardId);
        ParentsBoardReplyDTO savedReply = parentsBoardReplyService.parentsBoardReplySave(parentsBoardReplyDTO, memberId, parentsBoardId);


        return savedReply;
    }

    @PostMapping("delete/{replyId}")
    @ResponseBody
    public void removeParentsBoardReply(@PathVariable("replyId") Long replyId) {
        log.info("replyId : " + replyId);
        parentsBoardReplyService.removeByParentsBoardReply(replyId);
    }

    @GetMapping("list/show/{page}/{boardId}")
    @ResponseBody
    public Map<String, Object> getParentsBoardReplyList(@PathVariable("page") Integer page,@PathVariable Long boardId) {
        Page<ParentsBoardReplyDTO> parentsBoardReplyDTOS = parentsBoardReplyService.findAllReplyByBoardIdWithPaging(PageRequest.of(page, 3), boardId);
        int count = parentsBoardReplyDTOS.getTotalPages();
        Map<String, Object> response = new HashMap<>();
        response.put("data", parentsBoardReplyDTOS);
        log.info("----------------------" + parentsBoardReplyDTOS.toString());
        response.put("count", count);
        return response;
    }

    @GetMapping("show/{boardId}")
    @ResponseBody
    public Long getParentsBoardReplyCount(@PathVariable Long boardId) {
        Long count = parentsBoardReplyService.parentsBoardReplyCount(boardId);
        log.info("CounT : " + count);
        return count;
    }


    //    댓글 수정
    @PostMapping("update/{replyId}/{replyContent}")
    @ResponseBody
    public void updateByParentsBoardReply(@PathVariable("replyId") Long replyId, @PathVariable("replyContent") String replyContent) {
        parentsBoardReplyService.updateByParentsBoardReply(replyId, replyContent);
    }
}
