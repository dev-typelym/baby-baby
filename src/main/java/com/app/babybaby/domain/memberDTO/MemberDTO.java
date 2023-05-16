package com.app.babybaby.domain.memberDTO;

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

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class MemberDTO {

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

//    public static class MemberDTOBuilder {
//        private Long id;
//        private String  memberEmail;
//        private String memberName;
//        private String memberPassword;
//        private String memberNickname;
//        private String memberHiSentence;
//        private String memberPhone;
//        private Address memberAddress;
//        private String memberProfileOriginalName;
//        private String memberProfileUUID;
//        private String memberProfilePath;
//        private LocalDateTime memberRegisterDate;
//        private MemberType memberType;
//        private Role memberRole;
//        private AcceptanceType memberGuideStatus;
//        private SleepType memberSleep;
//        private GuideType memberGuideType;
//        private CategoryType memberGuideInterest;
//        private String memberFilePath;
//        private String memberFileUUID;
//        private String memberFileOriginalName;
//        private List<Alert> alerts;
//        private List<Coupon> coupons;
//        private List<Event> events;
//        private List<ReviewDTO> reviews;
//        private LocalDateTime uploadTime;
//
//        public MemberDTOBuilder id(Long id) {
//            this.id = id;
//            return this;
//        }
//
//        public MemberDTOBuilder memberEmail(String memberEmail) {
//            this.memberEmail = memberEmail;
//            return this;
//        }
//
//        public MemberDTOBuilder memberName(String memberName) {
//            this.memberName = memberName;
//            return this;
//        }
//
//        public MemberDTOBuilder memberPassword(String memberPassword) {
//            this.memberPassword = memberPassword;
//            return this;
//        }
//
//        public MemberDTOBuilder memberNickname(String memberNickname) {
//            this.memberNickname = memberNickname;
//            return this;
//        }
//
//        public MemberDTOBuilder memberHiSentence(String memberHiSentence) {
//            this.memberHiSentence = memberHiSentence;
//            return this;
//        }
//
//        public MemberDTOBuilder memberPhone(String memberPhone) {
//            this.memberPhone = memberPhone;
//            return this;
//        }
//
//        public MemberDTOBuilder memberAddress(Address memberAddress) {
//            this.memberAddress = memberAddress;
//            return this;
//        }
//
//        public MemberDTOBuilder memberProfileOriginalName(String memberProfileOriginalName) {
//            this.memberProfileOriginalName = memberProfileOriginalName;
//            return this;
//        }
//
//        public MemberDTOBuilder memberProfileUUID(String memberProfileUUID) {
//            this.memberProfileUUID = memberProfileUUID;
//            return this;
//        }
//
//        public MemberDTOBuilder memberProfilePath(String memberProfilePath) {
//            this.memberProfilePath = memberProfilePath;
//            return this;
//        }
//
//        public MemberDTOBuilder memberRegisterDate(LocalDateTime memberRegisterDate) {
//            this.memberRegisterDate = memberRegisterDate;
//            return this;
//        }
//
//        public MemberDTOBuilder memberType(MemberType memberType) {
//            this.memberType = memberType;
//            return this;
//        }
//
//        public MemberDTOBuilder memberRole(Role memberRole) {
//            this.memberRole = memberRole;
//            return this;
//        }
//
//        public MemberDTOBuilder memberGuideStatus(AcceptanceType memberGuideStatus) {
//            this.memberGuideStatus = memberGuideStatus;
//            return this;
//        }
//
//        public MemberDTOBuilder memberSleep(SleepType memberSleep) {
//            this.memberSleep = memberSleep;
//            return this;
//        }
//
//        public MemberDTOBuilder memberGuideType(GuideType memberGuideType) {
//            this.memberGuideType = memberGuideType;
//            return this;
//        }
//
//        public MemberDTOBuilder memberGuideInterest(CategoryType memberGuideInterest) {
//            this.memberGuideInterest = memberGuideInterest;
//            return this;
//        }
//
//        public MemberDTOBuilder memberFilePath(String memberFilePath) {
//            this.memberFilePath = memberFilePath;
//            return this;
//        }
//
//        public MemberDTOBuilder memberFileUUID(String memberFileUUID) {
//            this.memberFileUUID = memberFileUUID;
//            return this;
//        }
//
//        public MemberDTOBuilder memberFileOriginalName(String memberFileOriginalName) {
//            this.memberFileOriginalName = memberFileOriginalName;
//            return this;
//        }
//        public MemberDTOBuilder alerts(List<Alert> alerts) {
//            this.alerts = alerts;
//            return this;
//        }
//
//        public MemberDTOBuilder coupons(List<Coupon> coupons) {
//            this.coupons = coupons;
//            return this;
//        }
//
//        public MemberDTOBuilder events(List<Event> events) {
//            this.events = events;
//            return this;
//        }
//
//        public MemberDTOBuilder reviews(List<ReviewDTO> reviews) {
//            this.reviews = reviews;
//            return this;
//        }
//
//        public MemberDTOBuilder uploadTime(LocalDateTime uploadTime) {
//            this.uploadTime = uploadTime;
//            return this;
//        }
//
////        public Member build() {
////            return new Member(this);
////        }
//
//    }

    @Builder(builderClassName = "DTOBuilder", builderMethodName = "DTOBuilder")
    public MemberDTO(Long id, String memberEmail, String memberName, String memberPassword, String memberNickname, String memberHiSentence, String memberPhone, Address memberAddress, String memberProfileOriginalName, String memberProfileUUID, String memberProfilePath, LocalDateTime memberRegisterDate, MemberType memberType, Role memberRole, AcceptanceType memberGuideStatus, SleepType memberSleep, GuideType memberGuideType, CategoryType memberGuideInterest, String memberFilePath, String memberFileUUID, String memberFileOriginalName, List<Alert> alerts, List<Coupon> coupons, LocalDateTime uploadTime) {
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
    }
}
