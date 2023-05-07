package com.app.babybaby.entity.purchase.coupon;

import com.app.babybaby.entity.user.User;
import com.app.babybaby.type.CouponStatus;
import com.app.babybaby.type.CouponType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = "user")
@Table(name = "TBL_COUPON")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {
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
    @JoinColumn(name = "USER_ID")
    private User user;

    public Coupon(CouponType couponType, CouponStatus couponStatus, Long couponPrice, User user) {
        this.couponType = couponType;
        this.couponStatus = couponStatus;
        this.couponPrice = couponPrice;
        this.user = user;
    }
}
