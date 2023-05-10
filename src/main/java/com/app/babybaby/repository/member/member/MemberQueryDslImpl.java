package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.babybaby.entity.member.QMember.member;

@RequiredArgsConstructor
public class MemberQueryDslImpl implements MemberQueryDsl {
    private final JPAQueryFactory query;

//    memberId 로 나의 쿠폰 목록
    @Override
    public List<Member> findCouponByMemberId(Long memberId) {
        return query.select(member)
                .from(member)
                .join(member.coupons)
                .fetchJoin()
                .where(member.id.eq(memberId))
                .fetch();
    }
}
