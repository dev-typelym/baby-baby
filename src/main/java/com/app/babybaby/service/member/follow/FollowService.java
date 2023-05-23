package com.app.babybaby.service.member.follow;

import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FollowService {
    public void saveFollow(Long followId, Long followerId);

    public void deleteFollow(Long followId, Long followerId);

    public Page<MemberDTO> findFollowersByMemberId(Pageable pageable, Long memberId);

    public Page<MemberDTO> findFollowingsByMemberId(Pageable pageable, Long memberId);

    public Boolean getIsFollowedByMemberId(Long memberId, Long sessionId);

    public Long findFollowingMemberCountByMemberId_QueryDSL(Long memberId);

    default MemberDTO entityToMemberDTO(Member member){
        return MemberDTO.dtoBuilder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberNickname(member.getMemberNickname())
                .memberHiSentence(member.getMemberHiSentence())
                .memberPhone(member.getMemberPhone())
                .memberAddress(member.getMemberAddress())
                .memberProfileOriginalName(member.getMemberProfileOriginalName())
                .memberProfileUUID(member.getMemberProfileUUID())
                .memberProfilePath(member.getMemberProfilePath())
                .memberRegisterDate(member.getMemberRegisterDate())
                .memberType(member.getMemberType())
                .memberRole(member.getMemberRole())
                .memberGuideStatus(member.getMemberGuideStatus())
                .memberSleep(member.getMemberSleep())
                .memberGuideType(member.getMemberGuideType())
                .memberGuideInterest(member.getMemberGuideInterest())
                .memberFilePath(member.getMemberFilePath())
                .memberFileOriginalName(member.getMemberFileOriginalName())
                .memberFileUUID(member.getMemberFileUUID())
                .build();
    }

}
