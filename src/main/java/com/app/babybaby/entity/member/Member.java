package com.app.babybaby.entity.member;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.type.*;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @ToString(exclude = {"alerts"})
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
@SequenceGenerator(name="mem_seq", initialValue=1, allocationSize=1)
public class Member {

    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mem_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull @Column(unique = true)
    private String  memberEmail;
    @NotNull
    private String memberName;
    @NotNull
    private String memberPassword;
    @NotNull @Column(unique = true)
    private String memberNickname;
    @NotNull
    private String memberHiSentence;
    @NotNull @Column(unique = true)
    private String memberPhone;
    @Embedded @NotNull
    private Address memberAddress;
    @ColumnDefault("'defaultImage.png'")
    @NotNull
    private String memberProfileOriginalName;
    @ColumnDefault("'defaultImageUUID'")
    @Column(name = "MEMBER_PROFILE_UUID")
    @NotNull
    private String memberProfileUUID;
    @ColumnDefault("'defaultImage'")
    @NotNull
    private String memberProfilePath;
    @NotNull
    private LocalDateTime memberRegisterDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

//    로그인 시 멤버 종류 나누기 위한 column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role memberRole;

    /* 가이드 신청 시 HOLD -> 승인 후 가이드 ACCEPTED, 일반 회원 NULL */
    @Enumerated(EnumType.STRING)
    private AcceptanceType memberGuideStatus;

    /* 탈퇴(sleep)가 true*/
    /* 내일 강사님께 질문*/
    @NotNull @Enumerated(EnumType.STRING)
    @ColumnDefault("'AWAKE'")
    private SleepType memberSleep;
    /* 장애인/비장애인 통솔자 */
    @Enumerated(EnumType.STRING)
    private GuideType memberGuideType;
    /* 가이드 관심분야 */
    @Enumerated(EnumType.STRING)
    private CategoryType memberGuideInterest;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Alert> alerts = new ArrayList<>();

    private String memberFilePath;
    @Column(name = "MEMBER_FILE_UUID")
    private String memberFileUUID;
    private String memberFileOriginalName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Coupon> coupons;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<Event> events;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Review> reviews;

//    public static class MemberBuilder {
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
//        private List<Review> reviews;
//
//        public MemberBuilder(Long id, String memberEmail, String memberName, String memberPassword, String memberNickname, String memberHiSentence, String memberPhone, Address memberAddress, String memberProfileOriginalName, String memberProfileUUID, String memberProfilePath, LocalDateTime memberRegisterDate, MemberType memberType, Role memberRole, AcceptanceType memberGuideStatus, SleepType memberSleep, GuideType memberGuideType, CategoryType memberGuideInterest, String memberFilePath, String memberFileUUID, String memberFileOriginalName, List<Alert> alerts, List<Coupon> coupons, List<Event> events, List<Review> reviews) {
//            this.id = id;
//            this.memberEmail = memberEmail;
//            this.memberName = memberName;
//            this.memberPassword = memberPassword;
//            this.memberNickname = memberNickname;
//            this.memberHiSentence = memberHiSentence;
//            this.memberPhone = memberPhone;
//            this.memberAddress = memberAddress;
//            this.memberProfileOriginalName = memberProfileOriginalName;
//            this.memberProfileUUID = memberProfileUUID;
//            this.memberProfilePath = memberProfilePath;
//            this.memberRegisterDate = memberRegisterDate;
//            this.memberType = memberType;
//            this.memberRole = memberRole;
//            this.memberGuideStatus = memberGuideStatus;
//            this.memberSleep = memberSleep;
//            this.memberGuideType = memberGuideType;
//            this.memberGuideInterest = memberGuideInterest;
//            this.memberFilePath = memberFilePath;
//            this.memberFileUUID = memberFileUUID;
//            this.memberFileOriginalName = memberFileOriginalName;
//            this.alerts = alerts;
//            this.coupons = coupons;
//            this.events = events;
//            this.reviews = reviews;
//        }
//
//        public MemberBuilder id(Long id) {
//            this.id = id;
//            return this;
//        }
//
//        public MemberBuilder memberEmail(String memberEmail) {
//            this.memberEmail = memberEmail;
//            return this;
//        }
//
//        public MemberBuilder memberName(String memberName) {
//            this.memberName = memberName;
//            return this;
//        }
//
//        public MemberBuilder memberPassword(String memberPassword) {
//            this.memberPassword = memberPassword;
//            return this;
//        }
//
//        public MemberBuilder memberNickname(String memberNickname) {
//            this.memberNickname = memberNickname;
//            return this;
//        }
//
//        public MemberBuilder memberHiSentence(String memberHiSentence) {
//            this.memberHiSentence = memberHiSentence;
//            return this;
//        }
//
//        public MemberBuilder memberPhone(String memberPhone) {
//            this.memberPhone = memberPhone;
//            return this;
//        }
//
//        public MemberBuilder memberAddress(Address memberAddress) {
//            this.memberAddress = memberAddress;
//            return this;
//        }
//
//        public MemberBuilder memberProfileOriginalName(String memberProfileOriginalName) {
//            this.memberProfileOriginalName = memberProfileOriginalName;
//            return this;
//        }
//
//        public MemberBuilder memberProfileUUID(String memberProfileUUID) {
//            this.memberProfileUUID = memberProfileUUID;
//            return this;
//        }
//
//        public MemberBuilder memberProfilePath(String memberProfilePath) {
//            this.memberProfilePath = memberProfilePath;
//            return this;
//        }
//
//        public MemberBuilder memberRegisterDate(LocalDateTime memberRegisterDate) {
//            this.memberRegisterDate = memberRegisterDate;
//            return this;
//        }
//
//        public MemberBuilder memberType(MemberType memberType) {
//            this.memberType = memberType;
//            return this;
//        }
//
//        public MemberBuilder memberRole(Role memberRole) {
//            this.memberRole = memberRole;
//            return this;
//        }
//
//        public MemberBuilder memberGuideStatus(AcceptanceType memberGuideStatus) {
//            this.memberGuideStatus = memberGuideStatus;
//            return this;
//        }
//
//        public MemberBuilder memberSleep(SleepType memberSleep) {
//            this.memberSleep = memberSleep;
//            return this;
//        }
//
//        public MemberBuilder memberGuideType(GuideType memberGuideType) {
//            this.memberGuideType = memberGuideType;
//            return this;
//        }
//
//        public MemberBuilder memberGuideInterest(CategoryType memberGuideInterest) {
//            this.memberGuideInterest = memberGuideInterest;
//            return this;
//        }
//
//        public MemberBuilder memberFilePath(String memberFilePath) {
//            this.memberFilePath = memberFilePath;
//            return this;
//        }
//
//        public MemberBuilder memberFileUUID(String memberFileUUID) {
//            this.memberFileUUID = memberFileUUID;
//            return this;
//        }
//
//        public MemberBuilder memberFileOriginalName(String memberFileOriginalName) {
//            this.memberFileOriginalName = memberFileOriginalName;
//            return this;
//        }
//        public MemberBuilder alerts(List<Alert> alerts) {
//            this.alerts = alerts;
//            return this;
//        }
//
//        public MemberBuilder coupons(List<Coupon> coupons) {
//            this.coupons = coupons;
//            return this;
//        }
//
//        public MemberBuilder events(List<Event> events) {
//            this.events = events;
//            return this;
//        }
//
//        public MemberBuilder reviews(List<Review> reviews) {
//            this.reviews = reviews;
//            return this;
//        }
//
////        public Member build() {
////            return new Member(this);
////        }
//
//    }

    @Builder(builderClassName = "ShortBuilder", builderMethodName = "ShortBuilder")
    public Member(String memberEmail, String memberName, String memberPassword, String memberNickname, String memberHiSentence, String memberPhone, Address memberAddress, String memberProfileOriginalName, String memberProfileUUID, String memberProfilePath, LocalDateTime memberRegisterDate, MemberType memberType, AcceptanceType memberGuideStatus, SleepType memberSleep, GuideType memberGuideType, CategoryType memberGuideInterest, String memberFilePath, String memberFileUUID, String memberFileOriginalName) {
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
        this.memberGuideStatus = memberGuideStatus;
        this.memberSleep = memberSleep;
        this.memberGuideType = memberGuideType;
        this.memberGuideInterest = memberGuideInterest;
        this.memberFilePath = memberFilePath;
        this.memberFileUUID = memberFileUUID;
        this.memberFileOriginalName = memberFileOriginalName;
    }
    @Builder(builderClassName = "LongerBuilder", builderMethodName = "LongerBuilder")
    public Member(Long id, String memberEmail, String memberName, String memberPassword, String memberNickname, String memberHiSentence, String memberPhone, Address memberAddress, String memberProfileOriginalName, String memberProfileUUID, String memberProfilePath, LocalDateTime memberRegisterDate, MemberType memberType, AcceptanceType memberGuideStatus, SleepType memberSleep, GuideType memberGuideType, CategoryType memberGuideInterest, List<Alert> alerts, String memberFilePath, String memberFileUUID, String memberFileOriginalName) {
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
        this.memberGuideStatus = memberGuideStatus;
        this.memberSleep = memberSleep;
        this.memberGuideType = memberGuideType;
        this.memberGuideInterest = memberGuideInterest;
        this.alerts = alerts;
        this.memberFilePath = memberFilePath;
        this.memberFileUUID = memberFileUUID;
        this.memberFileOriginalName = memberFileOriginalName;
    }

    @Builder(builderClassName = "joinMemberBuilder", builderMethodName = "joinMemberBuilder") /* 회원 가입 용 */
    public Member(Long id, String memberEmail, String memberName, String memberPassword, String memberNickname, String memberHiSentence, String memberPhone, Address memberAddress, String memberProfileOriginalName, String memberProfileUUID, String memberProfilePath, LocalDateTime memberRegisterDate, MemberType memberType, Role memberRole) {
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
    }
}
