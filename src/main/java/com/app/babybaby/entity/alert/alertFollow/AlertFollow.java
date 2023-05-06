package com.app.babybaby.entity.alert.alertFollow;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.user.Follow;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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
}