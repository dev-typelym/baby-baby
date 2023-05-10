package com.app.babybaby.repository.member.follow;


import com.app.babybaby.entity.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FollowQueryDsl {
    public Slice<Member> findFollowersByMemberId(Pageable pageable, Long memberId);

    public Slice<Member> findFollowingsByMemberId(Pageable pageable, Long memberId);
}
