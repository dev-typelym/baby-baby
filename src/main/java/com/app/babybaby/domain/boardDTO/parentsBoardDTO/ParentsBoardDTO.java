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
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class ParentsBoardDTO {
    private Long id;
    private String eventTitle;
    private String memberNickname;
    private Long memberId;
    private CategoryType eventCategory;
    private Long eventId;
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
    @Builder
    public ParentsBoardDTO(Long id, String eventTitle, String memberNickname, Long memberId, CategoryType eventCategory, Long eventId, String parentsBoardContent, String parentsBoardTitle, String memberProfileUUID, String memberProfilePath, String memberProfileOriginalName, LocalDateTime parentsBoardRegisterDate, LocalDateTime parentsBoardUpdateDate, String representFileOriginName, String representFilePath, String representFileUUID, List<ParentsBoardFileDTO> parentsBoardFileDTOS, List<ParentsBoardReplyDTO> parentsBoardReplyDTOS, LocalDate parentsBoardRegisterDateType, LocalDate parentsBoardUpdateDateType) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.eventCategory = eventCategory;
        this.eventId = eventId;
        this.parentsBoardContent = parentsBoardContent;
        this.parentsBoardTitle = parentsBoardTitle;
        this.memberProfileUUID = memberProfileUUID;
        this.memberProfilePath = memberProfilePath;
        this.memberProfileOriginalName = memberProfileOriginalName;
        this.parentsBoardRegisterDate = parentsBoardRegisterDate;
        this.parentsBoardUpdateDate = parentsBoardUpdateDate;
        this.representFileOriginName = representFileOriginName;
        this.representFilePath = representFilePath;
        this.representFileUUID = representFileUUID;
        this.parentsBoardFileDTOS = parentsBoardFileDTOS;
        this.parentsBoardReplyDTOS = parentsBoardReplyDTOS;
        this.parentsBoardRegisterDateType = parentsBoardRegisterDateType;
        this.parentsBoardUpdateDateType = parentsBoardUpdateDateType;
    }
}