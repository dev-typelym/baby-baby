package com.app.babybaby.entity.user;

import com.app.babybaby.entity.audit.Period;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.GuideType;
import com.app.babybaby.type.SleepType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

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
public class User extends Period {

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

    /* 탈퇴(sleep)가 true*/
    /* 내일 강사님께 질문*/
    @NotNull @Enumerated(EnumType.STRING)
    @ColumnDefault("'AWAKE'")
    private SleepType userSleep;
    @Enumerated(EnumType.STRING)
    private GuideType userGuideType;
    @Enumerated(EnumType.STRING)
    private CategoryType userGuideInterest;
    private String userResumeFilePath;
    @Column(name = "USER_RESUME_UUID")
    private String userResumeFileUUID;
    private String userResumeFileOriginalName;

//    @Column(name = "USER_PROFILE_ORIGINAL_NAME")
//    @ColumnDefault("defaultImage.png")
//    @NotNull
//    private String userProfileOriginalName;
//
//    @Column(name = "USER_PROFILE_UUID")
//    @ColumnDefault("defaultImage.png")
//    @NotNull
//    private String userProfileUUID;
//
//    @Column(name = "USER_PROFILE_PATH")
//    @ColumnDefault("/defaultImage")
//    @NotNull
//    private String userProfilePath;
//
//    /* 탈퇴(sleep)가 true*/
//    /* 내일 강사님께 질문*/
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @ColumnDefault("'AWAKE'")
//    private SleepType userSleep;
//
//    @Enumerated(EnumType.STRING)
//    private GuideType userGuideType;
//
//    @Enumerated(EnumType.STRING)
//    private CategoryType userGuideInterest;
//
//    private String userResumeFilePath;
//
//    private String userResumeFileUUID;
//
//    private String userResumeFileOriginalName;
}
