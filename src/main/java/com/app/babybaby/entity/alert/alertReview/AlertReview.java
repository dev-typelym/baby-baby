package com.app.babybaby.entity.alert.alertReview;

import com.app.babybaby.entity.alert.Alert;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.type.AlertType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"review"})
@Table(name = "TBL_ALERT_REVIEW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlertReview extends Alert {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID")
    private Review review;

    public AlertReview(Review review) {
        this.review = review;
    }

//    public AlertReview(String alertTitle, String alertContent, AlertType alertType, LocalDateTime alertRegisterDate, Review review) {
//        super(alertTitle, alertContent, alertType, alertRegisterDate);
//        this.review = review;
//    }
}
