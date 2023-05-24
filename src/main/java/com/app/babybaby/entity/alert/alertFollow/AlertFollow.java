package com.app.babybaby.entity.alert.alertFollow;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.member.Follow;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.AlertReadStatus;
import com.app.babybaby.type.AlertType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"follower"})
@Table(name = "TBL_ALERT_FOLLOW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlertFollow extends Alert {

    @ManyToOne(fetch = FetchType.LAZY)
    private Follow follower;

    public AlertFollow(Follow follower) {
        this.follower = follower;
    }

    @Builder
    public AlertFollow(Long id, String alertTitle, String alertContent, AlertType alertType, Member member, LocalDateTime alertRegisterDate, AlertReadStatus alertReadStatus, Follow follower) {
        super(id, alertTitle, alertContent, alertType, member, alertRegisterDate, alertReadStatus);
        this.follower = follower;
    }

}