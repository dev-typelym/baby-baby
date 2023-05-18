package com.app.babybaby.domain.replyDTO.reviewReplyDTO;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class ReviewReplyDTO {
    private Long replyId;
    private String ReviewReplyContent;
}
