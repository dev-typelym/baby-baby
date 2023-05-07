package com.app.babybaby.entity.alert;

import com.app.babybaby.type.AlertType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
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

    private LocalDateTime alertRegisterDate;

    public Alert(String alertTitle, String alertContent, AlertType alertType, LocalDateTime alertRegisterDate) {
        this.alertTitle = alertTitle;
        this.alertContent = alertContent;
        this.alertType = alertType;
        this.alertRegisterDate = alertRegisterDate;
    }
}
