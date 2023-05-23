package com.app.babybaby.domain.memberDTO;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.type.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class MemberDTO implements Serializable {

    private Long id;
    private String  memberEmail;
    private String memberName;
    private String memberPassword;
    private String memberNickname;
    private String memberHiSentence;
    private String memberPhone;
    private Address memberAddress;
    private String memberProfileOriginalName;
    private String memberProfileUUID;
    private String memberProfilePath;
//    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime memberRegisterDate;
    private MemberType memberType;
    private Role memberRole;
    private AcceptanceType memberGuideStatus;
    private SleepType memberSleep;
    private GuideType memberGuideType;
    private CategoryType memberGuideInterest;
    private String memberFilePath;
    private String memberFileUUID;
    private String memberFileOriginalName;
    private List<Alert> alerts;
    private List<Coupon> coupons;

//    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime uploadTime;

    private List<ReviewDTO> reviews;

    private List<ParentsBoardDTO> parentsBoards;

    private Long followerCount;
    private Long followingCount;

    private Boolean isFollowed;

public MemberDTO(Member member) {
    this.memberName = member.getMemberName();
    this.memberPhone = member.getMemberPhone();
    this.memberEmail = member.getMemberEmail();
}
    @Builder(builderClassName = "DTOBuilder", builderMethodName = "dtoBuilder")
    public MemberDTO(Long id, String memberEmail, String memberName, String memberPassword, String memberNickname, String memberHiSentence, String memberPhone, Address memberAddress, String memberProfileOriginalName, String memberProfileUUID, String memberProfilePath, LocalDateTime memberRegisterDate, MemberType memberType, Role memberRole, AcceptanceType memberGuideStatus, SleepType memberSleep, GuideType memberGuideType, CategoryType memberGuideInterest, String memberFilePath, String memberFileUUID, String memberFileOriginalName, List<Alert> alerts, List<Coupon> coupons, LocalDateTime uploadTime, List<ReviewDTO> reviews, List<ParentsBoardDTO> parentsBoards, Long followerCount, Long followingCount) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberHiSentence = memberHiSentence;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.memberProfileOriginalName = memberProfileOriginalName;
        this.memberProfileUUID = memberProfileUUID;
        this.memberProfilePath = memberProfilePath;
        this.memberRegisterDate = memberRegisterDate;
        this.memberType = memberType;
        this.memberRole = memberRole;
        this.memberGuideStatus = memberGuideStatus;
        this.memberSleep = memberSleep;
        this.memberGuideType = memberGuideType;
        this.memberGuideInterest = memberGuideInterest;
        this.memberFilePath = memberFilePath;
        this.memberFileUUID = memberFileUUID;
        this.memberFileOriginalName = memberFileOriginalName;
        this.alerts = alerts;
        this.coupons = coupons;
        this.uploadTime = uploadTime;
        this.reviews = reviews;
        this.parentsBoards = parentsBoards;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }
}
