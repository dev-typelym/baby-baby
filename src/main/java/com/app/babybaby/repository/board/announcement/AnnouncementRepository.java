package com.app.babybaby.repository.board.announcement;

import com.app.babybaby.entity.board.announcement.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, AnnouncementQueryDsl {
}
