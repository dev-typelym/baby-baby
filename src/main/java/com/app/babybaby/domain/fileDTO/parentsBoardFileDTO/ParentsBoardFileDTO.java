package com.app.babybaby.domain.fileDTO.parentsBoardFileDTO;


import com.app.babybaby.type.FileType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ParentsBoardFileDTO {
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String fileOriginalName;
    private String filePath;
    private FileType fileStatus;
    private String fileUUID;
    private Long id;
}
