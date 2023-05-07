package com.app.babybaby.repository.alert.alertParentsBoard;

import com.app.babybaby.entity.alert.alertParentsBoard.AlertParentsBoard;
import com.app.babybaby.repository.alert.alertFollow.AlertFollowQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertParentsBoardRepository extends JpaRepository<AlertParentsBoard, Long>, AlertFollowQueryDsl {
}
