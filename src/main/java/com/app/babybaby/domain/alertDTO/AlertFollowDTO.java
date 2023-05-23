package com.app.babybaby.domain.alertDTO;

import com.app.babybaby.type.AlertReadStatus;
import com.app.babybaby.type.AlertType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Component
@Data
@RequiredArgsConstructor
public class AlertFollowDTO {
    private Long id;
    private Long memberId;
    private String memberNickname;
    private String alertTitle;
    private String alertContent;
    private AlertType alertType;
    private AlertReadStatus alertReadStatus;

}
