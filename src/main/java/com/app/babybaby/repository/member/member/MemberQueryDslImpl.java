package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.QMember;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.entity.purchase.coupon.QCoupon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.member.QFollow.follow;
import static com.app.babybaby.entity.member.QMember.member;
import static com.app.babybaby.entity.purchase.coupon.QCoupon.coupon;

@RequiredArgsConstructor
public class MemberQueryDslImpl implements MemberQueryDsl {
    private final JPAQueryFactory query;

    /* 이메일 중복 검사 */
    @Override
    public Optional<Member> overlapByMemberEmail_QueryDSL(String memberEmail) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne());
    }

    /* 핸드폰 중복 검사 */
    @Override
    public Optional<Member> overlapByPhone_QueryDSL(String memberPhone) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberPhone.eq(memberPhone)).fetchOne());
    }

    /* 닉네임 중복 검사 */
    @Override
    public Optional<Member> overlapByMemberNickname_QueryDSL(String memberNickname) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberNickname.eq(memberNickname)).fetchOne());
    }

    /* 비밀번호, 이메일 찾기 */
    @Override
    public Optional<Member> findByMemberEmail_QueryDSL(String memberEmail) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne());
    }

    /* 비번 변경 */
    @Override
    public void updatePassword_QueryDSL(Long id, String memberPassword) {
        query.update(member).set(member.memberPassword, memberPassword).where(member.id.eq(id)).execute();
    }

}
