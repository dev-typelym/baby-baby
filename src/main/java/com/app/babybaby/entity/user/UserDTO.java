package com.app.babybaby.entity.user;

import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.type.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class UserDTO {
    private Long id;
    private String  userEmail;
    private String userName;
    private String userPassword;
    private String userNickname;
    private String userHiSentence;
    private String userPhone;
    private Address userAddress;
    private String userProfileOriginalName;
    private String userProfileUUID;
    private String userProfilePath;
    private LocalDateTime userRegisterDate;
    private UserType userType;
    private AcceptanceType userGuideStatus;
    private SleepType userSleep;
    private GuideType userGuideType;
    private CategoryType userGuideInterest;
    private String userResumeFilePath;
    private String userResumeFileUUID;
    private String userResumeFileOriginalName;



}
