package com.app.babybaby.repository.alert.alertFollow;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlertFollowQueryDsl {
//    강사님
//    public List<Member> getFollowers(Long memberId);
    public List<Member> getFollowers(Long memberId);

//    알림 버튼을 눌렀을 떄 뿌려줄 팔로우 알림 리스트 8개
    public List<AlertFollow> find8DescByMemberId(Long memberId);

//    알림 버튼을 눌렀을 떄 뿌려줄 팔로우 알림 리스트 8개
    public List<Follow> find8RecentFollowersByMemberId(Long memberId);

}