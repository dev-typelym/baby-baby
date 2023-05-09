package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
}
