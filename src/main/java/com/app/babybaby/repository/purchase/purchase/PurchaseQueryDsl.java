package com.app.babybaby.repository.purchase.purchase;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.PurchaseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PurchaseQueryDsl {
    public Slice<PurchaseDTO> findAllByUserWithDetail_QueryDSL(Pageable pageable, Member member);
}
