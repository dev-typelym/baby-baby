package com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@RequiredArgsConstructor
public class ParentsBoardReplyDTO {
    private Long id;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String parentsBoardReplyContent;
    private Long memberId;
    private String memberNickName;
    private String memberFileOriginalName;
    private String memberFilePath;
    private String memberFileUUID;
    private Long parentsBoardId;

    @Builder
    public ParentsBoardReplyDTO(Long id, LocalDateTime registerDate, LocalDateTime updateDate, String parentsBoardReplyContent, Long memberId, String memberNickName, String memberFileOriginalName, String memberFilePath, String memberFileUUID, Long parentsBoardId) {
        this.id = id;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.parentsBoardReplyContent = parentsBoardReplyContent;
        this.memberId = memberId;
        this.memberNickName = memberNickName;
        this.memberFileOriginalName = memberFileOriginalName;
        this.memberFilePath = memberFilePath;
        this.memberFileUUID = memberFileUUID;
        this.parentsBoardId = parentsBoardId;
    }
}

