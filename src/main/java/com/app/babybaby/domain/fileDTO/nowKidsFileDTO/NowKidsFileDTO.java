package com.app.babybaby.domain.fileDTO.nowKidsFileDTO;


import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.type.FileType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class NowKidsFileDTO {
    private Long nowKidsId;
    private String fileOriginalName;
    private String fileUUID;
    private String filePath;
    private FileType fileType;
    private String fineSize;


    @Builder
    public NowKidsFileDTO(Long nowKidsId, String fileOriginalName, String fileUUID, String filePath, FileType fileType, String fineSize) {
        this.nowKidsId = nowKidsId;
        this.fileOriginalName = fileOriginalName;
        this.fileUUID = fileUUID;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fineSize = fineSize;
    }
}
