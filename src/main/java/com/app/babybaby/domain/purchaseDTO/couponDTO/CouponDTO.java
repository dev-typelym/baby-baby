package com.app.babybaby.domain.purchaseDTO.couponDTO;

import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.CouponStatus;
import com.app.babybaby.type.CouponType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class CouponDTO {
    private Long id;
    private CouponType couponType;
    private CouponStatus couponStatus;
    private Long couponPrice;
    private Long memberId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;


    @Builder
    public CouponDTO(Long id, CouponType couponType, CouponStatus couponStatus, Long couponPrice, Long memberId, LocalDateTime registerDate, LocalDateTime updateDate) {
        this.id = id;
        this.couponType = couponType;
        this.couponStatus = couponStatus;
        this.couponPrice = couponPrice;
        this.memberId = memberId;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

}
