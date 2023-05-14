package com.app.babybaby.controller.alertController;


import com.app.babybaby.service.alert.alertCrew.AlertCrewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/alert/*")
@RequiredArgsConstructor
@Slf4j
public class AlertCrewController {

    private final AlertCrewService alertCrewService;

}
