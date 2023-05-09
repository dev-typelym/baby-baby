package com.app.babybaby.entity.member;

import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.type.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
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
    private LocalDateTime memberRegisterDate;
    private MemberType memberType;
    private AcceptanceType memberGuideStatus;
    private SleepType memberSleep;
    private GuideType memberGuideType;
    private CategoryType memberGuideInterest;
    private String memberResumeFilePath;
    private String memberResumeFileUUID;
    private String memberResumeFileOriginalName;



}
