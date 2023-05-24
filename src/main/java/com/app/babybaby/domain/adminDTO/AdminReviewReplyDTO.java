package com.app.babybaby.domain.adminDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminReviewReplyDTO {
    private Long id;
    private String reviewTitle;
    private LocalDateTime writeDate;
    private String replyContent;
}
