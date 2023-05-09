package com.app.babybaby.entity.alert.alertFollow;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.type.AlertType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"follow"})
@Table(name = "TBL_ALERT_FOLLOW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlertFollow extends Alert {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOW_ID")
    private Follow follow;

    public AlertFollow(Follow follow) {
        this.follow = follow;
    }

    public AlertFollow(String alertTitle, String alertContent, AlertType alertType, LocalDateTime alertRegisterDate, Follow follow) {
        super(alertTitle, alertContent, alertType, alertRegisterDate);
        this.follow = follow;
    }
}