package com.app.babybaby.controller.errorController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("service")
@Slf4j
public class ServicePreparingController {

    @GetMapping("service-preparing")
    public String handlePreparingPage(){
        return "/error/service-preparing";
    }
}
