package com.app.babybaby.service.purchase.purchase;

import com.app.babybaby.domain.purchaseDTO.couponDTO.CouponDTO;
import com.app.babybaby.domain.purchaseDTO.purchaseDTO.PurchaseDTO;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PurchaseService {
    //    나의 쿠폰 조회
    public Page<PurchaseDTO> findAllByMemberPaymentWithPage(Pageable pageable, Long memberId);


    default PurchaseDTO PurchaseToDTO(Purchase purchase){
        return PurchaseDTO.builder()
                .coupon(purchase.getCoupon())
                .eventTitle(purchase.getEvent().getBoardTitle())
                .id(purchase.getId())
                .memberId(purchase.getMember().getId())
                .purchaseCount(purchase.getPurchaseCount())
                .purchasePrice(purchase.getPurchasePrice())
                .purchaseRegisterDate(purchase.getPurchaseRegisterDate())
                .build();
    }

}
