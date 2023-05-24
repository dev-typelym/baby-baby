package com.app.babybaby.domain.adminDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminParentsBoardReplyDTO {
    private Long id;
    private String parentsBoardTitle;
    private LocalDateTime writeDate;
    private String replyContent;
}
