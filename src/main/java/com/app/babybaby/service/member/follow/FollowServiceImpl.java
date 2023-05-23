package com.app.babybaby.service.member.follow;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.alert.alertFollow.AlertFollowRepository;
import com.app.babybaby.repository.member.follow.FollowRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.service.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("follow") @Primary
@Slf4j
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final AlertFollowRepository alertFollowRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final HttpSession session;


    @Override
    public void saveFollow(Long followerId, Long followingId) {
        Member follower = memberRepository.findById(followerId).get();
        Member following = memberRepository.findById(followingId).get();
        Follow follow1 = new Follow(follower, following);
//        AlertFollow alertFollow = new AlertFollow(
//                a
//        );
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//        현재 로그인한 아이디
        Long memberId = memberDTO.getId();

//        if(following.getId()==memberId) {
//            alertFollowRepository.save()
//        }
        followRepository.save(follow1);
    }

    @Override @Transactional
    public void deleteFollow(Long followingId, Long followerId) {
        Member follower = memberRepository.findById(followerId).get();
        Member following = memberRepository.findById(followingId).get();
        followRepository.deleteFollowByFollowerAndFollowing(follower, following);
    }

    @Override
    public Page<MemberDTO> findFollowersByMemberId(Pageable pageable, Long memberId) {
        Page<Member> followers = followRepository.findFollowersByMemberId(pageable, memberId);
        List<MemberDTO> lists = followers.get().map(this::entityToMemberDTO).collect(Collectors.toList());
        return new PageImpl<>(lists, pageable, followers.getTotalElements());
    }

    @Override
    public Page<MemberDTO> findFollowingsByMemberId(Pageable pageable, Long memberId) {
        Page<Member> followings = followRepository.findFollowingsByMemberId(pageable, memberId);
        List<MemberDTO> lists = followings.get().map(this::entityToMemberDTO).collect(Collectors.toList());
        return new PageImpl<>(lists, pageable, followings.getTotalElements());
    }

    @Override
    public Boolean getIsFollowedByMemberId(Long memberId, Long sessionId) {
        return followRepository.getIsFollowedByMemberId(memberId, sessionId);
    }

    @Override
    public Long findFollowingMemberCountByMemberId_QueryDSL(Long memberId) {
        return followRepository.findFollowingMemberCountByMemberId_QueryDSL(memberId);
    }


}
