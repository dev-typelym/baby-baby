package com.app.babybaby.domain.boardDTO.parentsBoardDTO;

import com.app.babybaby.domain.fileDTO.parentsBoardFileDTO.ParentsBoardFileDTO;
import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.type.CategoryType;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
public class ParentsBoardDTO {
    private Long id;
    private String eventTitle;
    private String memberNickname;
    private CategoryType eventCategory;
    private String parentsBoardContent;
    private String parentsBoardTitle;
    private String memberProfileUUID;
    private String memberProfilePath;
    private String memberProfileOriginalName;
    private LocalDateTime parentsBoardRegisterDate;
    private LocalDateTime parentsBoardUpdateDate;
    private String representFileOriginName;
    private String representFilePath;
    private String representFileUUID;
    private List<ParentsBoardFileDTO> parentsBoardFileDTOS;
    private List<ParentsBoardReplyDTO> parentsBoardReplyDTOS;
    private LocalDate parentsBoardRegisterDateType;
    private LocalDate parentsBoardUpdateDateType;
}