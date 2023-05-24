package com.app.babybaby.entity.purchase.purchase;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString(exclude = {"coupon", "event", "member"})
@Table(name = "TBL_PURCHASE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Purchase {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private LocalDateTime purchaseRegisterDate;
    @NotNull
    @ColumnDefault("1")
    private Long purchaseCount;
    @NotNull
    private Long purchasePrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPON_ID")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Purchase(Long id, LocalDateTime purchaseRegisterDate, Long purchaseCount, Long purchasePrice, Coupon coupon, Event event, Member member) {
        this.id = id;
        this.purchaseRegisterDate = purchaseRegisterDate;
        this.purchaseCount = purchaseCount;
        this.purchasePrice = purchasePrice;
        this.coupon = coupon;
        this.event = event;
        this.member = member;
    }

    public Purchase(Long purchaseCount, Long purchasePrice, Coupon coupon, Event event, Member member) {
        this.purchaseCount = purchaseCount;
        this.purchasePrice = purchasePrice;
        this.coupon = coupon;
        this.event = event;
        this.member = member;
    }
}
