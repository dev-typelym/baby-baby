package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;

import java.util.List;

public interface MemberQueryDsl {
    public List<Member> findCouponByMemberId(Long memberId);
}
