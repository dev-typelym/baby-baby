package com.app.babybaby.service.file.announcementFile;

import com.app.babybaby.domain.fileDTO.announcementFileDTO.AnnouncementFileDTO;
import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.file.announcementFile.AnnouncementFile;
import com.app.babybaby.repository.board.announcement.AnnouncementRepository;
import com.app.babybaby.repository.file.announcementFile.AnnouncementFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementFileServiceImpl implements AnnouncementFileService {
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementFileRepository announcementFileRepository;

    @Override
    public void saveAnnouncementAllFiles(List<AnnouncementFileDTO> files, Long announcementId) {
        List<AnnouncementFile> announcementFiles = files.stream()
                .peek(announcementFileDTO -> announcementFileDTO.setAnnouncementId(announcementId))
                .map(this::toAnnouncementFileEntity)
                .collect(Collectors.toList());
        Announcement announcement = announcementRepository.findById(announcementId).get();
        announcementFiles.stream().peek(announcementFile -> announcementFile.setAnnouncement(announcement)).collect(Collectors.toList());
        announcementFileRepository.saveAll(announcementFiles);
    }
}
