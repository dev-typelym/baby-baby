package com.app.babybaby.repository.file.announcementFile;

import com.app.babybaby.entity.file.announcementFile.AnnouncementFile;

import java.util.List;

public interface AnnouncementFileQueryDsl {

    public List<AnnouncementFile> findByAnnouncementId(Long id);
}
