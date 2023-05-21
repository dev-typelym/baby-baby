package com.app.babybaby.controller.alertController;


import com.app.babybaby.entity.alert.alertCrew.AlertCrew;
import com.app.babybaby.service.alert.alertCrew.AlertCrewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Controller
@RequestMapping("/alert/*")
@RequiredArgsConstructor
@Slf4j
public class AlertCrewController {

    private final AlertCrewService alertCrewService;

    @GetMapping("list")
    public String getAlert() {
        return "myPage/myPage-alarm";
    }

    @GetMapping("/list/mypage")
    @ResponseBody
    public List<String> getAlertList() {
        Long memberId = 1L;
        return alertCrewService.getAlertList(memberId);
    }

//    @GetMapping("/list/mypage")
//    @ResponseBody
//    public String getAlertList() {
//        Long memberId = 1L;
//        String result = "";
//        alertCrewService.getAlertList(memberId);
//        for(int i = 0; i<alertCrewService.getAlertList(memberId).size(); i++){
//            result += alertCrewService.getAlertList(memberId).get(0)+"";
//        }
//        return result;
//    }
}
