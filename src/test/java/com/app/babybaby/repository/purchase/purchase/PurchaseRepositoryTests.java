package com.app.babybaby.repository.purchase.purchase;

import com.app.babybaby.entity.purchase.purchase.Purchase;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.service.purchase.purchase.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class PurchaseRepositoryTests {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    private MemberRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void saveTest(){
        for (int i = 0; i < 1; i++) {
            Purchase purchase = new Purchase(LocalDateTime.now(), 12L, 150000L, null, eventRepository.findById(296L).get(), memberRepository.findById(1L).get());
            purchaseRepository.save(purchase);
        }
    }


    @Test
    public void findAllByUserWithDetail_QueryDSL_Test() {
        userRepository.findById(1L).ifPresent(user -> {
            Page<Purchase> purchaseDTOS = purchaseRepository.findAllByMemberPaymentWithPage_QueryDSL(PageRequest.of(0, 5), 1L);
            purchaseDTOS.get().map(purchase -> purchase.toString()).forEach(log::info);
        });
    }
}
