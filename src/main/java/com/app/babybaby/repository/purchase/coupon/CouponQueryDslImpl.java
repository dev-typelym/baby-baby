package com.app.babybaby.repository.purchase.coupon;

import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.type.CouponStatus;
import com.app.babybaby.type.CouponType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.babybaby.entity.purchase.coupon.QCoupon.coupon;

@RequiredArgsConstructor
public class CouponQueryDslImpl implements CouponQueryDsl {
    private final JPAQueryFactory query;

    //    memberId 로 나의 쿠폰 목록
    @Override
    public Slice<Coupon> findCouponByMemberId_QueryDSL(Pageable pageable, Long memberId) {
        List<Coupon> memberCoupons = query.select(coupon)
                .from(coupon)
                .where(coupon.member.id.eq(memberId))
                .orderBy(coupon.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (memberCoupons.size() > pageable.getPageSize()) {
            hasNext = true;
            memberCoupons.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(memberCoupons, pageable, hasNext);
    }


    @Override
    public List<Coupon> findAllUnusedCoupon(Long memberId){
        return query.select(coupon)
                .from(coupon)
                .join(coupon.member)
                .where(coupon.couponStatus.eq(CouponStatus.UNUSED))
                .where(coupon.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public Long totalCouponCount(Long memberId){
        return query.select(coupon)
                .from(coupon)
                .join(coupon.member)
                .where(coupon.member.id.eq(memberId))
                .fetch()
                .stream()
                .count();
    }

    @Override
    public Long totalUnusedCouponCount(Long memberId){
        return query.select(coupon)
                .from(coupon)
                .join(coupon.member)
                .where(coupon.member.id.eq(memberId))
                .where(coupon.couponStatus.eq(CouponStatus.UNUSED))
                .fetch()
                .stream()
                .count();
    }

    @Override
    public Long totalMyCouponCount(Long memberId) {
        return null;
    }
}
