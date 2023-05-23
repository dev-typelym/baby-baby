package com.app.babybaby.service.purchase.purchase;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.domain.purchaseDTO.purchaseDTO.PurchaseDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.purchase.coupon.CouponRepository;
import com.app.babybaby.repository.purchase.purchase.PurchaseRepository;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;

    private final CouponRepository couponRepository;

    private final EventRepository eventRepository;

    private final MemberRepository memberRepository;

    private final MemberService memberService;

//    구매내역
    @Override
    public Page<PurchaseDTO> findAllByMemberPaymentWithPage(Pageable pageable, Long memberId) {
        Page<Purchase> purchases = purchaseRepository.findAllByMemberPaymentWithPage_QueryDSL(pageable, memberId);
        List<PurchaseDTO> purchaseDTOS = purchases.stream().map(purchase -> PurchaseToDTO(purchase)).collect(Collectors.toList());

        return new PageImpl<>(purchaseDTOS,pageable,purchases.getTotalElements());
    }

//    내가 참여한 이벤트요 ....
    @Override
    public Page<PurchaseDTO> findAllByEventWithPage(Pageable pageable, Long memberId, LocalDateTime startDate) {
        Page<Purchase> purchases = purchaseRepository.findAllByEventWithPage_QueryDSL(pageable, memberId,startDate);
        List<PurchaseDTO> purchaseDTOS = purchases.stream().map(purchase -> PurchaseToDTO(purchase)).collect(Collectors.toList());

        return new PageImpl<>(purchaseDTOS,pageable,purchases.getTotalElements());
    }

    //    구매 상세
    @Override
    public PurchaseDTO findMemberIdByPaymentDetail(Long purchaseId) {
        Purchase purchase = purchaseRepository.findMemberIdByPaymentDetail_QueryDSL(purchaseId);
        PurchaseDTO purchaseDTO = PurchaseToDTO(purchase);
        return purchaseDTO;
    }

    @Override
    public EventDTO findAllInfoForPayment(Long memberId, Long eventId){
        Member member = memberRepository.findById(memberId).get();
        Event event = eventRepository.findById(eventId).get();
        EventDTO eventDTO = this.eventToDTO(event);
        List<Coupon> coupons = couponRepository.findAllUnusedCoupon(memberId);
        eventDTO.setCoupons(coupons.stream().map(this::CouponToDTO).collect(Collectors.toList()));
        eventDTO.setTotalUnusedCouponCount(couponRepository.totalUnusedCouponCount(memberId));
        eventDTO.setTotalCouponCount(couponRepository.totalCouponCount(memberId));

        return eventDTO;
    }

}
