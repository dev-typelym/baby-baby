package com.app.babybaby.repository.member.follow;

import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowQueryDsl {
    public void deleteFollowByFollowerAndFollowing(Member follower, Member following);

    public List<Follow> findAllByFollowingId(Long sessionId);
}
