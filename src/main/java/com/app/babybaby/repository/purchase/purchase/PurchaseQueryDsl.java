package com.app.babybaby.repository.purchase.purchase;

import com.app.babybaby.entity.purchase.PurchaseDTO;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import com.app.babybaby.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PurchaseQueryDsl {
    public Slice<PurchaseDTO> findAllByUserWithDetail_QueryDSL(Pageable pageable, User user);
}
