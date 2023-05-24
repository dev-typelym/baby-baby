package com.app.babybaby.entity.alert.alertParentsBoard;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
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
@ToString(callSuper = true, exclude = {"parentsBoard"})
@Table(name = "TBL_ALERT_PARENTS_BOARD")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlertParentsBoard extends Alert {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTS_BOARD_ID")
    private ParentsBoard parentsBoard;

    public AlertParentsBoard(ParentsBoard parentsBoard) {
        this.parentsBoard = parentsBoard;
    }

//    public AlertParentsBoard(String alertTitle, String alertContent, AlertType alertType, Member member, LocalDateTime alertRegisterDate, ParentsBoard parentsBoard) {
//        super(alertTitle, alertContent, alertType, member, alertRegisterDate);
//        this.parentsBoard = parentsBoard;
//    }
}