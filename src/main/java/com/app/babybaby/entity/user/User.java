package com.app.babybaby.entity.user;

import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.GuideType;
import com.app.babybaby.type.SleepType;
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
    @ColumnDefault("defaultImage.png")
    @NotNull
    private String userProfileOriginalName;
    @ColumnDefault("defaultImage.png")
    @NotNull
    private String userProfileUUID;
    @ColumnDefault("/defaultImage")
    @NotNull
    private String userProfilePath;

    /* 탈퇴(sleep)가 true*/
    /* 내일 강사님께 질문*/
    @NotNull @Enumerated(EnumType.STRING)
<<<<<<< HEAD
    private SleepType userSleep=SleepType.AWAKE;

=======
    @ColumnDefault("'AWAKE'")
    private SleepType userSleep;
    @Enumerated(EnumType.STRING)
>>>>>>> mine
    private GuideType userGuideType;
    @Enumerated(EnumType.STRING)
    private CategoryType userGuideInterest;
    private String userResumeFilePath;
    private String userResumeFileUUID;
    private String userResumeFileOriginalName;


}
