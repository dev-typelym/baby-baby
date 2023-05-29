package com.app.babybaby.domain.purchaseDTO.purchaseDTO;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.type.CouponStatus;
import com.app.babybaby.type.CouponType;
import com.app.babybaby.type.GuideType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class PurchaseDTO {
    private Long id;
    private LocalDateTime purchaseRegisterDate;
    private Long purchaseCount;
    private Long purchasePrice;
    private Coupon coupon;
    private String eventTitle;
    private Long memberId;
    private String memberName;
    private String memberPhone;
    private GuideType guideType;
    private List<EventFileDTO> eventFileDTOS;
    private CalendarDTO calendarDTOS;
    private List<KidDTO> kids;
    Long [] kidList;
    private String eventRegisterDate;

    private CouponStatus couponStatus;
    private Long couponPrice;
    private CouponType couponType;

    @Builder
    public PurchaseDTO(Long id, LocalDateTime purchaseRegisterDate, Long purchaseCount, Long purchasePrice, Coupon coupon, String eventTitle, Long memberId, String memberName, String memberPhone, GuideType guideType, List<EventFileDTO> eventFileDTOS, CalendarDTO calendarDTOS, List<KidDTO> kids, Long[] kidList, String eventRegisterDate, CouponStatus couponStatus, Long couponPrice, CouponType couponType) {
        this.id = id;
        this.purchaseRegisterDate = purchaseRegisterDate;
        this.purchaseCount = purchaseCount;
        this.purchasePrice = purchasePrice;
        this.coupon = coupon;
        this.eventTitle = eventTitle;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.guideType = guideType;
        this.eventFileDTOS = eventFileDTOS;
        this.calendarDTOS = calendarDTOS;
        this.kids = kids;
        this.kidList = kidList;
        this.eventRegisterDate = eventRegisterDate;
        this.couponStatus = couponStatus;
        this.couponPrice = couponPrice;
        this.couponType = couponType;
    }
}
