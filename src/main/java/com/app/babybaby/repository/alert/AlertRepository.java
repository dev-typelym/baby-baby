package com.app.babybaby.repository.alert;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlertRepository extends JpaRepository<Alert, Long>, AlertQueryDsl {
    @Query("select a from Alert a where a.member.id = :memberId")
    public Slice<Alert> findAllByMemberId(Long memberId);


}
