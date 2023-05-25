package com.app.babybaby.domain.replyDTO.reviewReplyDTO;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class ReviewReplyDTO {
    private Long id;
    private String ReviewReplyContent;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private Long memberId;
    private String memberNickName;
    private String memberFileOriginalName;
    private String memberFilePath;
    private String memberFileUUID;
    private Long reviewId;

    @Builder
    public ReviewReplyDTO(Long id, String reviewReplyContent, LocalDateTime registerDate, LocalDateTime updateDate, Long memberId, String memberNickName, String memberFileOriginalName, String memberFilePath, String memberFileUUID, Long reviewId) {
        this.id = id;
        ReviewReplyContent = reviewReplyContent;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.memberId = memberId;
        this.memberNickName = memberNickName;
        this.memberFileOriginalName = memberFileOriginalName;
        this.memberFilePath = memberFilePath;
        this.memberFileUUID = memberFileUUID;
        this.reviewId = reviewId;
    }
}


