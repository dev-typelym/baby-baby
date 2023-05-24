package com.app.babybaby.domain.fileDTO.announcementFileDTO;

import com.app.babybaby.type.FileType;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@RequiredArgsConstructor
public class AnnouncementFileDTO {
    private Long id;
    private Long announcementId;
    private String fileOriginalName;
    private String fileUUID;
    private String filePath;
    private FileType fileType;

    @Builder
    public AnnouncementFileDTO(Long id, Long announcementId, String fileOriginalName, String fileUUID, String filePath, FileType fileType) {
        this.id = id;
        this.announcementId = announcementId;
        this.fileOriginalName = fileOriginalName;
        this.fileUUID = fileUUID;
        this.filePath = filePath;
        this.fileType = fileType;
    }
}
