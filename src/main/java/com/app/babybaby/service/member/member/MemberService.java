package com.app.babybaby.service.member.member;

import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.member.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MemberService {

    public Optional<Member> getMemberById(Long memberId);

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .memberNickname(member.getMemberNickname())
                .id(member.getId())
                .memberProfileUUID(member.getMemberProfileUUID())
                .memberProfilePath(member.getMemberProfilePath())
                .memberProfileOriginalName(member.getMemberProfileOriginalName())
                .build();
    }
}
