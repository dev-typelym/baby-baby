package com.app.babybaby.service.alert.alertFollow;

import com.app.babybaby.domain.alertDTO.AlertFollowDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.memberDTO.FollowDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.entity.member.Member;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public interface AlertFollowService {


//    public List<AlertFollowDTO> find8DescByMemberId(Long memberId);

//    강사님
    public List<MemberDTO> getFollowers(Long memberId);

//    public List<FollowDTO> getFollowers(Long memberId); // 세션에서 값 불러오기



//    알림 상태 변경
    public void updateReadStatus(Long id);

//    default AlertFollowDTO toAlertFollowDTO(AlertFollow alertFollow) {
//        return AlertFollowDTO.builder()
//                .id(alertFollow.getId())
//                .alertTitle(alertFollow.getAlertTitle())
//                .alertContent(alertFollow.getAlertContent())
//                .alertType(alertFollow.getAlertType())
//                .member(alertFollow.getMember())
//                .alertRegisterDate(alertFollow.getAlertRegisterDate())
//                .follower(alertFollow.getFollower())
//                .build();
//    }

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
    //    헤더 팔로우 알림 목록 8개
    public List<MemberDTO> find8RecentFollowersByMemberId(Long memberId);

//    default AlertFollow alertDTOtoEntity(AlertFollowDTO alertFollowDTO){
//        return AlertFollow.builder()
//                .id(alertFollowDTO.getId())
//                .alertTitle(alertFollowDTO.getAlertTitle())
//                .alertContent(alertFollowDTO.getAlertContent())
//                .alertType(alertFollowDTO.getAlertType())
//                .member(alertFollowDTO.getMember())
//                .alertRegisterDate(alertFollowDTO.getAlertRegisterDate())
//                .alertReadStatus(alertFollowDTO.getAlertReadStatus())
//                .follower(alertFollowDTO.getFollower())
//                .build();
//    }

    public AlertFollow saveAlertDTOForFollowing(Follow follow);

    public AlertFollowDTO findAlertDTOForFollowing(Long memberId);

    public void updateAlertReadStatus(AlertFollow alertFollow);


    default FollowDTO entityToFollowDTO(Follow follow){
        return FollowDTO.builder()
                .alertReadStatus(follow.getAlertReadStatus())
                .followDate(follow.getFollowDate())
                .follower(follow.getFollower())
                .following(follow.getFollowing())
                .id(follow.getId())
                .build();
    }
}
