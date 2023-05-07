package com.app.babybaby.repository.alert.alertReview;

import com.app.babybaby.entity.alert.alertReview.AlertReview;
import com.app.babybaby.repository.alert.alertFollow.AlertFollowQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertReviewRepository extends JpaRepository<AlertReview, Long>, AlertFollowQueryDsl {
}
