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
@Getter @ToString(exclude = {"alerts", "coupons", "reviews", "randomKeys"})
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<RandomKey> randomKeys;

    public void updatePassword(String memberPassword){
        this.memberPassword = memberPassword;
    }

    @Builder(builderClassName = "OAuthBuilder", builderMethodName = "oAuthBuilder")
    public Member(String memberName, String memberPhone, String memberEmail, Role memberRole, String memberNickname) {
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberRole = memberRole;
        this.memberNickname = memberNickname;
    }

    public Member update(String memberName, String memberPhone, String memberEmail, String memberNickname){
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberNickname = memberNickname;
        this.memberRole = Role.GENERAL;

        return this;
    }

    @Builder(builderClassName = "ShortBuilder", builderMethodName = "shortBuilder")
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
    @Builder(builderClassName = "LongerBuilder", builderMethodName = "longerBuilder")
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

    @Builder(builderClassName = "JoinMemberBuilder", builderMethodName = "joinMemberBuilder") /* 회원 가입 용 */
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

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public void setMemberAddress(Address memberAddress) {
        this.memberAddress = memberAddress;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public void setMemberGuideStatus(AcceptanceType memberGuideStatus) {
        this.memberGuideStatus = memberGuideStatus;
    }

    public void setMemberSleep(SleepType memberSleep) {
        this.memberSleep = memberSleep;
    }

    public void setMemberGuideType(GuideType memberGuideType) {
        this.memberGuideType = memberGuideType;
    }

    public void setMemberGuideInterest(CategoryType memberGuideInterest) {
        this.memberGuideInterest = memberGuideInterest;
    }

    public void setMemberFilePath(String memberFilePath) {
        this.memberFilePath = memberFilePath;
    }

    public void setMemberFileUUID(String memberFileUUID) {
        this.memberFileUUID = memberFileUUID;
    }

    public void setMemberFileOriginalName(String memberFileOriginalName) {
        this.memberFileOriginalName = memberFileOriginalName;
    }

    public void setMemberHiSentence(String memberHiSentence) {
        this.memberHiSentence = memberHiSentence;
    }

    public void setMemberProfileOriginalName(String memberProfileOriginalName) {
        this.memberProfileOriginalName = memberProfileOriginalName;
    }

    public void setMemberProfileUUID(String memberProfileUUID) {
        this.memberProfileUUID = memberProfileUUID;
    }

    public void setMemberProfilePath(String memberProfilePath) {
        this.memberProfilePath = memberProfilePath;
    }
}
