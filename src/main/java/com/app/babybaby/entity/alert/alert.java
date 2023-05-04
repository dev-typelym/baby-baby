package com.app.babybaby.entity.alert;

import com.app.babybaby.type.AlertType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Table(name = "TBL_ALERT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class alert {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String alertTitle;

    @NotNull
    private String alertContent;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    @NotNull
    private LocalDateTime alertRegisterDate;

}
