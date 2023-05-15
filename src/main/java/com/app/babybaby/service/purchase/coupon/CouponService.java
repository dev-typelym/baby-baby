package com.app.babybaby.service.purchase.coupon;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.purchaseDTO.couponDTO.CouponDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public interface CouponService {
    //    나의 쿠폰 조회
    public Slice<CouponDTO> findCouponByMemberId(Pageable pageable, Long memberId);



    default CouponDTO CouponToDTO(Coupon coupon){
        return CouponDTO.builder()
                .couponPrice(coupon.getCouponPrice())
                .couponStatus(coupon.getCouponStatus())
                .couponType(coupon.getCouponType())
                .id(coupon.getId())
                .memberId(coupon.getMember().getId())
                .registerDate(coupon.getRegisterDate())
                .updateDate(coupon.getUpdateDate())
                .build();
    }



}
