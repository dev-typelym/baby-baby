package com.app.babybaby.service.purchase.purchase;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.domain.purchaseDTO.purchaseDTO.PurchaseDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Crew;
import com.app.babybaby.entity.member.Guide;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.member.crew.CrewRepository;
import com.app.babybaby.repository.member.guide.GuidRepository;
import com.app.babybaby.repository.member.kid.KidRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.purchase.coupon.CouponRepository;
import com.app.babybaby.repository.purchase.purchase.PurchaseRepository;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.type.GuideAvailableType;
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

    private final GuidRepository guidRepository;

    private final CrewRepository crewRepository;

    private final KidRepository kidRepository;

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

    public void processPayment(Long memberId, Long eventId, List<Kid> kids) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));
        Member company = null;
        if(memberId != null){
            company = memberRepository.findById(memberId).get();
        }


//        가장 최근 하나 가져오기
        Guide guide = guidRepository.findFirstByEventIdAndAvailableTypeOrderById(eventId, GuideAvailableType.AVAILABLE);

//         현재 들어온 아이들수와 남아있는 가이드의 아이들 수를 더한게 10보다 작다면
        if(guide.getCrews().stream().count() + kids.stream().count() >= 10){
//            가이드의 status를 불가능으로 만들고
            guide.setAvailableType(GuideAvailableType.UNAVAILABLE);
//            새로운 가이드 가져오기
            Guide newGuide = guidRepository.findFirstByEventIdAndAvailableTypeOrderById(eventId, GuideAvailableType.AVAILABLE);
            if(newGuide == null){
                Guide guide1 = new Guide(event, company);
                Guide savedGuide  = guidRepository.save(guide1);
                kids.stream().forEach(kid -> {
                    Crew crew = new Crew(kid, savedGuide);
                    crewRepository.save(crew);
                });
            } else{
                kids.stream().forEach(kid -> {
                    Crew crew = new Crew(kid, newGuide);
                    crewRepository.save(crew);
                });
            }
//            만약에 10보다 작으면 바로 처음 가져온 가이드에 저장
        } else if(guide.getCrews().stream().count() + kids.stream().count() < 10){
            kids.stream().forEach(kid -> {
                Crew crew = new Crew(kid, guide);
                crewRepository.save(crew);
            });
        }

    }

    @Override
    public void saveAll(Long memberId, Long eventId, PurchaseDTO purchaseDTO) {
//        Purchase purchase = this.dtoToPurchaseEntity(purchaseDTO);
//        이 맴버는 현재 세션에 있는 맴버아이디
        Member member = memberRepository.findById(memberId).get();
        Event event = eventRepository.findById(eventId).get();
        Coupon coupon = new Coupon(purchaseDTO.getCouponType(), purchaseDTO.getCouponStatus(), purchaseDTO.getCouponPrice(), member);
        Purchase purchase1 = new Purchase(purchaseDTO.getPurchaseCount(), purchaseDTO.getCouponPrice(),coupon, event, member);
        purchaseRepository.save(purchase1);
        Guide guide = new Guide(event, event.getCompany());
        guidRepository.save(guide);
        purchaseDTO.getKids().stream().map(kidDTO -> this.toKidDTO(kidRepository.findById(kidDTO.getId()).get()));
        List<Kid> kids = purchaseDTO.getKids().stream().map(this::toKid).collect(Collectors.toList());
        processPayment(memberId, eventId, kids);
//        purchaseDTO.getKids().stream().forEach(kid -> {
//            Crew crew = new Crew(kid, savedGuide);
//            crewRepository.save(crew);
//        });
    }

}
