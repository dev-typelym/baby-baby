package com.app.babybaby.repository.file.announcementFile;

import com.app.babybaby.entity.file.announcementFile.AnnouncementFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementFileRepository extends JpaRepository<AnnouncementFile, Long>, AnnouncementFileQueryDsl {
}
