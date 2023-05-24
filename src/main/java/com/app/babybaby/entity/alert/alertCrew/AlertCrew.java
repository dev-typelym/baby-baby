package com.app.babybaby.entity.alert.alertCrew;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.member.Crew;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.AlertType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"crew"})
@Table(name = "TBL_ALERT_CREW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlertCrew extends Alert {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREW_ID")
    private Crew crew;

//    public AlertCrew(String alertTitle, String alertContent, AlertType alertType, Member member, LocalDateTime alertRegisterDate, Crew crew) {
//        super(alertTitle, alertContent, alertType, member, alertRegisterDate);
//        this.crew = crew;
//    }
}
