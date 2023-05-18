package com.app.babybaby.domain.fileDTO.reviewFileDTO;

import com.app.babybaby.type.FileType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ReviewFileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUUID;
    private String filePath;
    private FileType fileStatus;

    @Builder
    public ReviewFileDTO(Long id, String fileOriginalName, String fileUUID, String filePath, FileType fileStatus) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUUID = fileUUID;
        this.filePath = filePath;
        this.fileStatus = fileStatus;
    }
}
