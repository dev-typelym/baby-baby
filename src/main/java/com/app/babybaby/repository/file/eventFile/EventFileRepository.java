package com.app.babybaby.repository.file.eventFile;

import com.app.babybaby.entity.file.eventFile.EventFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventFileRepository extends JpaRepository<EventFile, Long>, EventFileQueryDsl {
}
