package com.app.babybaby.domain.alertDTO;

import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.AlertReadStatus;
import com.app.babybaby.type.AlertType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class AlertFollowDTO {
 private Long id;
 private String alertTitle;
 private String alertContent;
 private AlertType alertType;
 private Member member;
 private LocalDateTime alertRegisterDate;
 private AlertReadStatus alertReadStatus;
 private List<MemberDTO> followers;

    @Builder
    public AlertFollowDTO(Long id, String alertTitle, String alertContent, AlertType alertType, Member member, LocalDateTime alertRegisterDate, AlertReadStatus alertReadStatus, List<MemberDTO> followers) {
        this.id = id;
        this.alertTitle = alertTitle;
        this.alertContent = alertContent;
        this.alertType = alertType;
        this.member = member;
        this.alertRegisterDate = alertRegisterDate;
        this.alertReadStatus = alertReadStatus;
        this.followers = followers;
    }

    @QueryProjection
    public AlertFollowDTO(Long id, String alertTitle, String alertContent, AlertType alertType, Member member, LocalDateTime alertRegisterDate) {
        this.id = id;
        this.alertTitle = alertTitle;
        this.alertContent = alertContent;
        this.alertType = alertType;
        this.member = member;
        this.alertRegisterDate = alertRegisterDate;
    }
}
