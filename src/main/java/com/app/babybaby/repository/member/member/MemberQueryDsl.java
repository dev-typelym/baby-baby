package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface MemberQueryDsl {

    /* 이메일 중복 검사 */
    public Optional<Member> overlapByMemberEmail_QueryDSL(String memberEmail);

    /* 휴대폰 중복 검사 */
    public Optional<Member> overlapByPhone_QueryDSL(String memberPhone);

    /* 닉네임 중복 검사 */
    public Optional<Member> overlapByMemberNickname_QueryDSL(String memberNickname);

    /* 비밀 번호, 이메일 찾기 */
    public Optional<Member> findByMemberEmail_QueryDSL(String memberEmail);

    /* 비밀 번호 변경 */
    public void updatePassword_QueryDSL(Long id, String memberPassword);

    /* 이메일로 회원 정보 조회 */

    /* 일반 회원 가입 */

    /* 기업 회원 가입 */

//   회원정보 수정
    public void setMemberInfoMyId(Member member);

}
