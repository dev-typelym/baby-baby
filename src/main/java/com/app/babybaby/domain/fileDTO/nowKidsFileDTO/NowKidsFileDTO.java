package com.app.babybaby.domain.fileDTO.nowKidsFileDTO;


import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.type.FileType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class NowKidsFileDTO {
    private Long id;
    private Long nowKidsId;
    private String fileOriginalName;
    private String fileUUID;
    private String filePath;
    private FileType fileType;
    private String fineSize;


    @Builder(builderClassName = "OrgFileBuilder", builderMethodName = "orgFileBuilder")
    public NowKidsFileDTO(Long nowKidsId, String fileOriginalName, String fileUUID, String filePath, FileType fileType, String fineSize) {
        this.nowKidsId = nowKidsId;
        this.fileOriginalName = fileOriginalName;
        this.fileUUID = fileUUID;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fineSize = fineSize;
    }

    @Builder(builderClassName = "FileBuilderForMain", builderMethodName = "fileBuilderForMain")
    public NowKidsFileDTO(Long id, Long nowKidsId, String fileOriginalName, String fileUUID, String filePath, FileType fileType, String fineSize) {
        this.id = id;
        this.nowKidsId = nowKidsId;
        this.fileOriginalName = fileOriginalName;
        this.fileUUID = fileUUID;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fineSize = fineSize;
    }

}
