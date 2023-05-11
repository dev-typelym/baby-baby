package com.app.babybaby.domain.memberDTO;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.type.*;
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
    @DateTimeFormat(pattern = "yyyy.MM.dd")
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


}
