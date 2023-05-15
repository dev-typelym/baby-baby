package com.app.babybaby.domain.purchaseDTO.purchaseDTO;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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


    @Builder
    public PurchaseDTO(Long id, LocalDateTime purchaseRegisterDate, Long purchaseCount, Long purchasePrice, Coupon coupon, String eventTitle, Long memberId) {
        this.id = id;
        this.purchaseRegisterDate = purchaseRegisterDate;
        this.purchaseCount = purchaseCount;
        this.purchasePrice = purchasePrice;
        this.coupon = coupon;
        this.eventTitle = eventTitle;
        this.memberId = memberId;
    }
}
