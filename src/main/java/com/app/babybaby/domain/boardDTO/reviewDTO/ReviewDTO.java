package com.app.babybaby.domain.boardDTO.reviewDTO;

import com.app.babybaby.domain.fileDTO.reviewFileDTO.ReviewFileDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.reviewFile.ReviewFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.reply.reviewReply.ReviewReply;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class ReviewDTO {
    private Long id;
    private int ReviewScore;
    private List<ReviewFileDTO> reviewFiles;
    private String boardTitle;
    private String boardContent;
    private Long memberId;

    @Builder
    public ReviewDTO(Long id, int reviewScore, List<ReviewFileDTO> reviewFiles, String boardTitle, String boardContent, Long memberId) {
        this.id = id;
        ReviewScore = reviewScore;
        this.reviewFiles = reviewFiles;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.memberId = memberId;
    }
}
