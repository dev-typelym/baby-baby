package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.event.QEvent;
import com.app.babybaby.entity.board.parentsBoard.QParentsBoard;
import com.app.babybaby.entity.board.review.QReview;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.QGuide;
import com.app.babybaby.entity.member.QMember;
import com.app.babybaby.entity.member.QRandomKey;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.entity.purchase.coupon.QCoupon;
import com.app.babybaby.search.admin.AdminMemberSearch;
import com.app.babybaby.type.AcceptanceType;
import com.app.babybaby.type.GuideType;
import com.app.babybaby.type.MemberType;
import com.app.babybaby.type.SleepType;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;
import static com.app.babybaby.entity.board.review.QReview.review;
import static com.app.babybaby.entity.member.QFollow.follow;
import static com.app.babybaby.entity.member.QMember.member;
import static com.app.babybaby.entity.member.QRandomKey.randomKey1;
import static com.app.babybaby.entity.purchase.coupon.QCoupon.coupon;

@RequiredArgsConstructor
public class MemberQueryDslImpl implements MemberQueryDsl {
    private final JPAQueryFactory query;

    /* 이메일 중복 검사 */
    @Override
    public Long overlapByMemberEmail_QueryDSL(String memberEmail) {
        return query.select(member.count()).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne();
    }

    /* 핸드폰 중복 검사 */
    @Override
    public Long overlapByPhone_QueryDSL(String memberPhone) {
        return query.select(member.count()).from(member).where(member.memberPhone.eq(memberPhone)).fetchOne();
    }

    /* 닉네임 중복 검사 */
    @Override
    public Long overlapByMemberNickname_QueryDSL(String memberNickname) {
        return query.select(member.count()).from(member).where(member.memberNickname.eq(memberNickname)).fetchOne();
    }

    /* 비밀번호, 이메일 찾기 */
    @Override
    public Member findByMemberEmail_QueryDSL(String memberEmail) {
        return query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne();
    }

    /* 랜덤키로 회원 찾기 */
    public Member findMemberByRandomKey(String randomKey){
        return query.select(member)
                .from(randomKey1)
                .join(randomKey1.member, member)
                .where(randomKey1.randomKey.eq(randomKey))
                .fetchOne();
    }

    /* 랜덤키로 회원 찾기 */
    @Override
    public Member findMemberByMemberEmailAndRandomKey(String memberEmail, String randomKey) {
        return query.select(member)
                .from(randomKey1)
                .join(randomKey1.member, member)
                .where(randomKey1.randomKey.eq(randomKey),randomKey1.member.memberEmail.eq(memberEmail))
                .fetchOne();
    }

    ;

    /*회원정보 수정*/
    @Override
    public void setMemberInfoMyId(Member member) {
        query.update(QMember.member)
                .set(QMember.member.memberNickname, member.getMemberNickname())
                .set(QMember.member.memberEmail, member.getMemberName())
                .set(QMember.member.memberPhone, member.getMemberPhone())
                .set(QMember.member.memberPassword, member.getMemberPassword())
                .set(QMember.member.memberAddress, member.getMemberAddress())
                .where(QMember.member.eq(member))
                .execute();
    }



    //    --------------------------------------------회원 상세 페이지---------------------------------------------------
    public Member getCompanyInfoByMemberId_QueryDSL(Long memberId) {
    return query.selectFrom(member)
            .leftJoin(member.events)
            .where(member.id.eq(memberId).and(member.memberType.eq(MemberType.COMPANY)))
            .fetchOne();
}
//----------------------------------------------관리자 페이지 ------------------------------------------------------------

    //  [관리자페이지]관리자 회원 전체 조회
    @Override
    public Page<Member> findAllMemberWithSearch_queryDSL(Pageable pageable, AdminMemberSearch memberSearch) {
        BooleanExpression memberNameEq = memberSearch.getMemberName() == null ? null : member.memberName.eq(memberSearch.getMemberName());

        List<Member> foundUsers = query.select(member)
                .from(member)
                .where((member.memberType.eq(MemberType.GENERAL).or(member.memberType.eq(MemberType.GENERAL_GUIDE)).or(member.memberType.eq(MemberType.ADMIN_GUIDE))).and(member.memberSleep.eq(SleepType.AWAKE)).and(memberNameEq))
                .orderBy(member.id.asc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(member.count())
                .from(member)
                .where((member.memberType.eq(MemberType.GENERAL).or(member.memberType.eq(MemberType.GENERAL_GUIDE)).or(member.memberType.eq(MemberType.ADMIN_GUIDE))).and(member.memberSleep.eq(SleepType.AWAKE)).and(memberNameEq))
                .fetchOne();

        return new PageImpl<>(foundUsers, pageable, count);
    }

    //    [관리자페이지]관리자 회원 상세보기
    public Optional<Member> findMemberInfoById_QueryDsl(Long memberId) {
        return Optional.ofNullable(query.select(member)
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne());
    }

    //    [관리자페이지]관리자 회원 삭제
    @Override
    public void disableMembersByIds_queryDSL(List<Long> memberIds) {
        query.update(member)
                .set(member.memberSleep, SleepType.SLEEP)
                .where(member.id.in(memberIds))
                .execute();
    }

    //   [관리자페이지]기업 전체 조회
    @Override
    public Page<Member> findAllCompanyWithSearch_queryDSL(Pageable pageable, AdminMemberSearch memberSearch) {
        BooleanExpression memberNameEq = memberSearch.getMemberName() == null ? null : member.memberName.eq(memberSearch.getMemberName());

        List<Member> foundUsers = query.select(member)
                .from(member)
                .where(member.memberType.eq(MemberType.COMPANY).and(memberNameEq))
                .orderBy(member.id.asc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(member.count())
                .from(member)
                .where(member.memberType.eq(MemberType.COMPANY).and(memberNameEq))
                .fetchOne();

        return new PageImpl<>(foundUsers, pageable, count);
    }


    // 관리자페이지 기업 행사개수
    @Override
    public Optional<Long> findCompanyOpenEventsCount_QueryDsl(Long companyId) {
        return Optional.ofNullable(query.select(event.count())
                .from(event)
                .join(event.company)
                .where(event.company.id.eq(companyId))
                .fetchOne());
    }


    // 관리자페이지 기업 상세보기
    public List<Event> findEventInfoBycompanyId_QueryDsl(Long companyId) {
        return query.select(event)
                .from(event)
                .join(event.company)
                .fetchJoin()
                .where(event.company.id.eq(companyId))
                .orderBy(event.id.asc())
                .fetch();
    }

    // 관리자페이지 가이드 신청 일반인 또는 가이드 목록 조회
    @Override
    public Page<Member> findAllGuideWithSearch_queryDSL(Pageable pageable, AdminMemberSearch memberSearch, GuideType guideType, AcceptanceType acceptanceType) {
        BooleanExpression memberNameEq = memberSearch.getMemberName() == null ? null : member.memberName.eq(memberSearch.getMemberName());

        List<Member> foundGuides = query.select(member)
                .from(member)
                .where((guideType != null ? member.memberGuideType.eq(guideType) : member.memberGuideType.isNotNull())
                                .and(member.memberGuideStatus.eq(acceptanceType))
                                .and(member.memberFileUUID.isNotNull())
                                .and(member.memberSleep.eq(SleepType.AWAKE))
                        .and(memberNameEq))
                .orderBy(member.id.asc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(member.count())
                .from(member)
                .where((guideType != null ? member.memberGuideType.eq(guideType) : member.memberGuideType.isNotNull())
                                .and(member.memberGuideStatus.eq(acceptanceType))
                                .and(member.memberFileUUID.isNotNull())
                                .and(member.memberSleep.eq(SleepType.AWAKE))
                        .and(memberNameEq))
                .fetchOne();

        return new PageImpl<>(foundGuides, pageable, count);
    }

    //  [관리자] 통솔자 승인하기 취소시 memberFile 지우기
    @Override
    public void deleteMemberFileById_queryDSL(Long memberId) {
        query.update(member)
                .setNull(member.memberFilePath)
                .setNull(member.memberFileOriginalName)
                .setNull(member.memberFileUUID)
                .where(member.id.eq(memberId))
                .execute();
    }


    //  [관리자] 통솔자 승인하기 취소시 가이드 흥미사항 지우기
    @Override
    public void deleteGuideInterestById_queryDSL(Long memberId) {
        query.update(member)
                .setNull(member.memberGuideInterest)
                .where(member.id.eq(memberId))
                .execute();
    }

    //  [관리자] 통솔자 승인하기 취소시 일반회원으로 돌리기
    @Override
    public void updateGuideStatusById_queryDSL(Long memberId, Long confirm) {

        AcceptanceType acceptStatus = null;
        MemberType guideStatus = null;
        if (confirm.equals("승인하기")) {
            acceptStatus = AcceptanceType.ACCEPTED;
            guideStatus = MemberType.GENERAL_GUIDE;
        } else if (confirm.equals("승인취소")) {
            acceptStatus = AcceptanceType.HOLD;
            guideStatus = MemberType.GENERAL;
            deleteMemberFileById_queryDSL(memberId);
            deleteGuideInterestById_queryDSL(memberId);
        }

        if (acceptStatus != null) {
            query.update(member)
                    .set(member.memberGuideStatus, acceptStatus)
                    .set(member.memberType, guideStatus)
                    .where(member.id.eq(memberId))
                    .execute();
        }
    }


}
