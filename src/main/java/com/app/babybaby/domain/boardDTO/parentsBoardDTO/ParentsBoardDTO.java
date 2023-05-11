package com.app.babybaby.domain.boardDTO.parentsBoardDTO;

import com.app.babybaby.domain.fileDTO.parentsBoardFileDTO.ParentsBoardFileDTO;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.type.CategoryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ParentsBoardDTO {
    private Long id;
    private String eventTitle;
    private CategoryType eventCategory;
    private String parentsBoardContent;
    private String parensBoardTitle;
    private LocalDateTime parentsBoardRegisterDate;
    private LocalDateTime parentsBoardUpdateDate;
    private String representFileOriginName;
    private String representFilePath;
    private String representFileUUID;
    private List<ParentsBoardFileDTO> parentsBoardFileDTOS;
}
