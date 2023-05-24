package com.app.babybaby.service.file.announcementFile;

import com.app.babybaby.domain.fileDTO.announcementFileDTO.AnnouncementFileDTO;
import com.app.babybaby.entity.file.announcementFile.AnnouncementFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnnouncementFileService {
    public void saveAnnouncementAllFiles(List<AnnouncementFileDTO> files, Long announcementId);

    default AnnouncementFile toAnnouncementFileEntity(AnnouncementFileDTO announcementFileDTO){
        return AnnouncementFile.builder()
                .fileOriginalName(announcementFileDTO.getFileOriginalName())
                .filePath(announcementFileDTO.getFilePath())
                .fileUUID(announcementFileDTO.getFileUUID())
                .fileStatus(announcementFileDTO.getFileType())
                .build();
    }
}
