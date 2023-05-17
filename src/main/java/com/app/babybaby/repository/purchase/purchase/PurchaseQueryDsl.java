package com.app.babybaby.repository.purchase.purchase;

import com.app.babybaby.domain.purchaseDTO.purchaseDTO.PurchaseDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;

public interface PurchaseQueryDsl {
    @EntityGraph
    public Page<Purchase> findAllByMemberPaymentWithPage_QueryDSL(Pageable pageable, Long memberId);
    public Page<Purchase> findAllByMemberPaymentFileWithPage_QueryDSL(Pageable pageable, Long memberId);

    public Purchase findMemberIdByPaymentDetail_QueryDSL(Long PurchaseId);
}
