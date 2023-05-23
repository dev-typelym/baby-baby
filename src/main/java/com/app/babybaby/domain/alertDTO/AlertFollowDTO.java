package com.app.babybaby.domain.alertDTO;

import com.app.babybaby.type.AlertReadStatus;
import com.app.babybaby.type.AlertType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Data
@RequiredArgsConstructor
public class AlertFollowDTO {
    private Long id;
    private Long memberId;
    private String memberNickname;
    private String alertContent;
    private AlertReadStatus alertReadStatus;
    private LocalDateTime insertTime;

    @Builder
    public AlertFollowDTO(Long id, Long memberId, String memberNickname, String alertContent, AlertReadStatus alertReadStatus, LocalDateTime insertTime) {
        this.id = id;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.alertContent = alertContent;
        this.alertReadStatus = alertReadStatus;
        this.insertTime = insertTime;
    }
}
