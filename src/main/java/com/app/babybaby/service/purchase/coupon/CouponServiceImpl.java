package com.app.babybaby.service.purchase.coupon;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.purchaseDTO.couponDTO.CouponDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.purchase.coupon.CouponRepository;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.type.CouponStatus;
import com.app.babybaby.type.CouponType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;


    @Override
    public Slice<CouponDTO> findCouponByMemberId(Pageable pageable, Long memberId) {
        Slice<Coupon> coupons = couponRepository.findAllByMemberId(memberId);
        List<CouponDTO> collect = coupons.get().map(coupon -> CouponToDTO(coupon)).collect(Collectors.toList());

        return new SliceImpl<>(collect, pageable, coupons.hasNext());
    }

    @Override
    public void saveCouponByParentsBoard(Long id) {
        Coupon coupon = new Coupon(CouponType.PARENT, CouponStatus.UNUSED, 5000L, memberRepository.findMemberById(id));
        couponRepository.save(coupon);
    }

//    @Override
//    public Slice<EventDTO> findEventListWithPaging(EventBoardSearch eventBoardSearch, Pageable pageable) {
//        Slice<Event> events = eventRepository.findEventListWithPaging_QueryDSL(eventBoardSearch, pageable);
//        events.get().map(event -> event.toString()).forEach(log::info);
//
//        List<EventDTO> collect = events.get().map(event -> eventToDTO(event)).collect(Collectors.toList());
////        List<EventDTO> collect = events.get().collect(Collectors.toList());
//        return new SliceImpl<>(collect,pageable,events.hasNext());
//    }


}
