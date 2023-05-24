package com.app.babybaby.entity.alert;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.AlertReadStatus;
import com.app.babybaby.type.AlertType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @ToString
@Table(name = "TBL_ALERT")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Alert {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String alertTitle;

    private String alertContent;

    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Member member;

    private LocalDateTime alertRegisterDate;

    @ColumnDefault("'UNREAD'")
    @Enumerated(EnumType.STRING)
    private AlertReadStatus alertReadStatus;

    private LocalDate followDate;

    public Alert(Long id, String alertTitle, String alertContent, AlertType alertType, Member member, LocalDateTime alertRegisterDate, AlertReadStatus alertReadStatus) {
        this.id = id;
        this.alertTitle = alertTitle;
        this.alertContent = alertContent;
        this.alertType = alertType;
        this.member = member;
        this.alertRegisterDate = alertRegisterDate;
        this.alertReadStatus = alertReadStatus;
    }

    //  ReadStatus는 유저가 알람을 보는 순간 READ로 업데이트가 되어야 한다.
    public void updateStatus(){
        this.alertReadStatus = AlertReadStatus.READ;
    }
//    public void setAlarm(AlarmDTO alarmDTO){
//        this.alertContent = alarmDTO.getAlertContent();
//        this.alertType = AlarmCategory.change(alarmDTO.getAlarmCategory());
//        this.contentId = alarmDTO.getContentId();
//    }
}
