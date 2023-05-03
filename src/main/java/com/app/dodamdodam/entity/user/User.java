package com.app.dodamdodam.entity.user;

import com.app.dodamdodam.type.Category;
import com.app.dodamdodam.type.GuideType;
import com.app.dodamdodam.type.SleepType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    @NotNull
    private Long id;
    @NotNull @Column(unique = true)
    private String  userEmail;
    @NotNull private String userName;
    @NotNull
    private String userPassword;
    @NotNull @Column(unique = true)
    private String userNickname;
    @NotNull private String userHiSentence;
    @NotNull @Column(unique = true)
    private String userPhone;
    @Embedded @NotNull
    private Address userAddress;
    @NotNull
    @ColumnDefault("defaultImage.png")
    private String userProfileOriginalName;
    @NotNull
    @ColumnDefault("defaultImage.png")
    private String userProfileUUID;
    @NotNull
    @ColumnDefault("/defaultImage")
    private String userProfilePath;

    /* 탈퇴(sleep)가 true*/
    /* 내일 강사님께 질문*/
    @NotNull @Enumerated(EnumType.STRING)
    private SleepType userSleep=SleepType.AWAKE;

    private GuideType userGuideType;
    private Category userGuideInterest;
    private String userResumeFilePath;
    private String userResumeFileUUID;
    private String userResumeFileOriginalName;


}
