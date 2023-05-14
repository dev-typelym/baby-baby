package com.app.babybaby.domain.fileDTO.reviewFileDTO;

import com.app.babybaby.type.FileType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewFileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUUID;
    private String filePath;
    private FileType fileStatus;
}
