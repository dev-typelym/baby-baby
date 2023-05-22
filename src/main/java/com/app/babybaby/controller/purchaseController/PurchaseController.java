package com.app.babybaby.controller.purchaseController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment/*")
public class PurchaseController {

    @GetMapping("pay")
    public String goPurchase(){
        return "payment/payment";
    }
}
