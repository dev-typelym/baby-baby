package com.app.babybaby.controller.alertController;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.service.alert.alertFollow.AlertFollowService;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/alert/follow/controller/*")
@RequiredArgsConstructor
@Slf4j
public class AlertFollowController {

    private AlertFollowService alertFollowService;

//    헤더 알림 갯수
    @GetMapping("unread")
    @ResponseBody
    public Long getUnread(HttpServletRequest request) {
//        Long count = (Long)request.getSession().getAttribute("noReadAlarm");
        Long count = (Long)request.getAttribute("noReadAlarm");
//        테스트 용도
        log.info("count : " + count);
        return count;
    }
    
//    헤더 알림 목록
    @GetMapping("list")
    @ResponseBody
    public List<AlertFollowDTO> getAlertList() {
        List<AlertFollowDTO> alertResult = alertFollowService.find8DescByMemberId();
        return alertResult;
    }

}
