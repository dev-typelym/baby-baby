package com.app.babybaby.repository.alert.alertFollow;

import com.app.babybaby.entity.alert.alertFollow.AlertFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertFollowRepository extends JpaRepository<AlertFollow, Long>, AlertFollowQueryDsl {
}
