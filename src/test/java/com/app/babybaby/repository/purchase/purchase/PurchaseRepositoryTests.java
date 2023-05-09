package com.app.babybaby.repository.purchase.purchase;

import com.app.babybaby.entity.purchase.PurchaseDTO;
import com.app.babybaby.repository.user.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class PurchaseRepositoryTests {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllByUserWithDetail_QueryDSL_Test() {
        userRepository.findById(1L).ifPresent(user -> {
            Slice<PurchaseDTO> purchaseDTOS = purchaseRepository.findAllByUserWithDetail_QueryDSL(PageRequest.of(0, 5), user);
            purchaseDTOS.get().map(PurchaseDTO::toString).forEach(log::info);
        });
    }
}
