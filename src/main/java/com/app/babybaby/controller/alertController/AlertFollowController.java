package com.app.babybaby.controller.alertController;

import com.app.babybaby.service.alert.alertFollow.AlertFollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
//        count = 1L;
        log.info("count : " + count);
        return count;
    }

}
