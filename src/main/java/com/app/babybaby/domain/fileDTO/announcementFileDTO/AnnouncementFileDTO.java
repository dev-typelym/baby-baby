package com.app.babybaby.domain.fileDTO.announcementFileDTO;

import com.app.babybaby.type.FileType;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@RequiredArgsConstructor
public class AnnouncementFileDTO {
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String fileOriginalName;
    private String filePath;
    private FileType fileType;
    private String fileUUID;
    private Long id;

    @Builder
    public AnnouncementFileDTO(LocalDateTime registerDate, LocalDateTime updateDate, String fileOriginalName, String filePath, FileType fileType, String fileUUID, Long id) {
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.fileOriginalName = fileOriginalName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileUUID = fileUUID;
        this.id = id;
    }
}
