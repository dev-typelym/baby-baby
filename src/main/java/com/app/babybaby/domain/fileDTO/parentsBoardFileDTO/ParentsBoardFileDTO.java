package com.app.babybaby.domain.fileDTO.parentsBoardFileDTO;


import com.app.babybaby.type.FileType;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@RequiredArgsConstructor
public class ParentsBoardFileDTO {
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String fileOriginalName;
    private String filePath;
    private FileType fileStatus;
    private String fileUUID;
    private Long id;

    @Builder
    public ParentsBoardFileDTO(LocalDateTime registerDate, LocalDateTime updateDate, String fileOriginalName, String filePath, FileType fileStatus, String fileUUID, Long id) {
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.fileOriginalName = fileOriginalName;
        this.filePath = filePath;
        this.fileStatus = fileStatus;
        this.fileUUID = fileUUID;
        this.id = id;
    }
}
