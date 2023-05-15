package com.app.babybaby.service.member.member;

import com.app.babybaby.entity.member.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MemberService {

    public Optional<Member> getMemberById(Long memberId);
}
