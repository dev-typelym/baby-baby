package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
    /* 이메일로 회원 정보 조회(MemberDetailService) */
    public Optional<Member> findByMemberEmail(String memberEmail);
    public Member findMemberById(Long Id);
}
