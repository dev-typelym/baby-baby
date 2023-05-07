package com.app.babybaby.entity.user;

import com.app.babybaby.entity.audit.Period;
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
@Table(name = "TBL_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class User {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull @Column(unique = true)
    private String  userEmail;
    @NotNull
    private String userName;
    @NotNull
    private String userPassword;
    @NotNull @Column(unique = true)
    private String userNickname;
    @NotNull
    private String userHiSentence;
    @NotNull @Column(unique = true)
    private String userPhone;
    @Embedded @NotNull
    private Address userAddress;
    @ColumnDefault("'defaultImage.png'")
    @NotNull
    private String userProfileOriginalName;
    @ColumnDefault("'defaultImage.png'")
    @Column(name = "USER_PROFILE_UUID")
    @NotNull
    private String userProfileUUID;
    @ColumnDefault("'/defaultImage'")
    @NotNull
    private String userProfilePath;
    @NotNull
    private LocalDateTime userRegisterDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;


    /* 가이드 신청 시 HOLD -> 승인 후 가이드 ACCEPTED, 일반 회원 NULL */
    @Enumerated(EnumType.STRING)
    private AcceptanceType userGuideStatus;

    /* 탈퇴(sleep)가 true*/
    /* 내일 강사님께 질문*/
    @NotNull @Enumerated(EnumType.STRING)
    @ColumnDefault("'AWAKE'")
    private SleepType userSleep;
    /* 장애인/비장애인 통솔자 */
    @Enumerated(EnumType.STRING)
    private GuideType userGuideType;
    /* 가이드 관심분야 */
    @Enumerated(EnumType.STRING)
    private CategoryType userGuideInterest;

    private String userResumeFilePath;
    @Column(name = "USER_RESUME_UUID")
    private String userResumeFileUUID;
    private String userResumeFileOriginalName;

    public User(String userEmail, String userName, String userPassword, String userNickname, String userHiSentence, String userPhone, Address userAddress, LocalDateTime userRegisterDate, UserType userType, AcceptanceType userGuideStatus, SleepType userSleep, GuideType userGuideType, CategoryType userGuideInterest) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userHiSentence = userHiSentence;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userRegisterDate = userRegisterDate;
        this.userType = userType;
        this.userGuideStatus = userGuideStatus;
        this.userSleep = userSleep;
        this.userGuideType = userGuideType;
        this.userGuideInterest = userGuideInterest;
    }
}
