package com.app.babybaby.repository.alert.alertCrew;

import com.app.babybaby.entity.alert.alertCrew.AlertCrew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertCrewRepository extends JpaRepository<AlertCrew, Long>, AlertCrewQueryDsl {
}
