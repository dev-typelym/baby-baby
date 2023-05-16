package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.search.admin.AdminMemberSearch;
import com.app.babybaby.type.AcceptanceType;
import com.app.babybaby.type.GuideType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface MemberQueryDsl {

    /* 이메일 중복 검사 */
    public Long overlapByMemberEmail_QueryDSL(String memberEmail);

    /* 휴대폰 중복 검사 */
    public Long overlapByPhone_QueryDSL(String memberPhone);

    /* 닉네임 중복 검사 */
    public Long overlapByMemberNickname_QueryDSL(String memberNickname);

    /* 비밀 번호, 이메일 찾기 */
    public Member findByMemberEmail_QueryDSL(String memberEmail);

    /* 비밀 번호 변경 */
    public void updatePassword_QueryDSL(Long id, String memberPassword);

    /* 회원정보 수정 */
    public void setMemberInfoMyId(Member member);

//    ------------------------------------------- 관리자페이지 -------------------------------------------
    //    관리자 회원 전체 목록 조회
    public Page<Member> findAllMemberWithSearch_queryDSL(Pageable pageable , AdminMemberSearch memberSearch);

    //    관리자 회원 상세보기
    public Optional<Member> findMemberInfoById_QueryDsl(Long memberId);

    //    관리자 회원 삭제
    public void disableMembersByIds_queryDSL(List<Long> memberIds);

    //    관리자 기업 전체 목록 조회
    public Page<Member> findAllCompanyWithSearch_queryDSL(Pageable pageable , AdminMemberSearch memberSearch);

    //    관리자 기업 진행 행사 수
    public Optional<Long> findCompanyOpenEventsCount_QueryDsl(Long companyId);

    //    관리자 기업이 진행한 행사목록 조회
    public List<Event> findEventInfoBycompanyId_QueryDsl(Long companyId);

    //    관리자 가이드 신청 일반인 또는 가이드 목록 조회
    public Page<Member> findAllGuideWithSearch_queryDSL(Pageable pageable, AdminMemberSearch memberSearch, GuideType guideType, AcceptanceType acceptanceType);

    //    관리자 통솔자 승인
    public void updateGuideStatusById_queryDSL(Long memberId, Long confirm);

    //    관리자 통솔자 취소시 memberfile 삭제하기
    public void deleteMemberFileById_queryDSL(Long memberId);

    //    관리자 통솔자 취소시 memberfile 삭제하기
    public void deleteGuideInterestById_queryDSL(Long memberId);

}
