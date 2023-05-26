package com.app.babybaby.service.purchase.purchase;

import com.app.babybaby.entity.member.Kid;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class PurchaseServiceTests {

    @Autowired
    PurchaseService purchaseService;

    List<Kid> kidList = new 


    @Test
    public void test1(){

        purchaseService.processPayment(30L, 175, );
    }

}
