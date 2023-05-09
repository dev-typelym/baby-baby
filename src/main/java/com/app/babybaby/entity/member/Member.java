package com.app.babybaby.entity.member;

import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.type.*;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Table(
//        name = "TBL_USER",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"userEmail", "userNickname", "userPhone"})
//        }
//)

@Entity
@Getter @ToString
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class Member {

    @Id @GeneratedValue
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
    @ColumnDefault("'defaultImage.png'")
    @Column(name = "MEMBER_PROFILE_UUID")
    @NotNull
    private String memberProfileUUID;
    @ColumnDefault("'/defaultImage'")
    @NotNull
    private String memberProfilePath;
    @NotNull
    private LocalDateTime memberRegisterDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberType memberType;


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

    private String memberResumeFilePath;
    @Column(name = "MEMBER_RESUME_UUID")
    private String memberResumeFileUUID;
    private String memberResumeFileOriginalName;

    public Member(String memberEmail, String memberName, String memberPassword, String memberNickname, String memberHiSentence, String memberPhone, Address memberAddress, LocalDateTime memberRegisterDate, memberType memberType, AcceptanceType memberGuideStatus, SleepType memberSleep, GuideType memberGuideType, CategoryType memberGuideInterest) {
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberHiSentence = memberHiSentence;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.memberRegisterDate = memberRegisterDate;
        this.memberType = memberType;
        this.memberGuideStatus = memberGuideStatus;
        this.memberSleep = memberSleep;
        this.memberGuideType = memberGuideType;
        this.memberGuideInterest = memberGuideInterest;
    }
}
