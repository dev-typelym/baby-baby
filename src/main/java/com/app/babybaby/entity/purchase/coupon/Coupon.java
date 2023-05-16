package com.app.babybaby.entity.purchase.coupon;

import com.app.babybaby.entity.audit.Period;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.CouponStatus;
import com.app.babybaby.type.CouponType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = {"member"})
@Table(name = "TBL_COUPON")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'UNUSED'")
    private CouponStatus couponStatus;

    @NotNull
    @ColumnDefault("5000")
    private Long couponPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Coupon(CouponType couponType, CouponStatus couponStatus, Long couponPrice, Member member) {
        this.couponType = couponType;
        this.couponStatus = couponStatus;
        this.couponPrice = couponPrice;
        this.member = member;
    }
}
