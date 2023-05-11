package com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ParentsBoardReplyDTO {
    private Long id;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String parentsBoardReplyContent;
    private Long memberId;
    private Long parentsBoardId;

}
