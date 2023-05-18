package com.app.babybaby.domain.boardDTO.reviewDTO;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.fileDTO.reviewFileDTO.ReviewFileDTO;
import com.app.babybaby.domain.replyDTO.reviewReplyDTO.ReviewReplyDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.file.reviewFile.ReviewFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.reply.reviewReply.ReviewReply;
import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.FileType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private int reviewScore;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime uploadDate;
    private LocalDateTime updateDate;
    private List<ReviewFileDTO> files;
    private List<ReviewReplyDTO> replies;

    private Long memberId;
    private String memberProfilePath;
    private String memberProfileUUID;
    private String memberProfileOriginalName;
    private String memberNickName;


    private Long eventId;
        private String eventTitle;
        private String eventContent;
        private Long eventRecruitCount;
        private Address eventLocation;
        private Long eventPrice;
        private CategoryType eventCategory;

    @Builder
    public ReviewDTO(Long id, int reviewScore, String boardTitle, String boardContent, LocalDateTime uploadDate, LocalDateTime updateDate, List<ReviewFileDTO> files, List<ReviewReplyDTO> replies, Long memberId, String memberProfilePath, String memberProfileUUID, String memberProfileOriginalName, String memberNickName, Long eventId, String eventTitle, String eventContent, Long eventRecruitCount, Address eventLocation, Long eventPrice, CategoryType eventCategory) {
        this.id = id;
        this.reviewScore = reviewScore;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.uploadDate = uploadDate;
        this.updateDate = updateDate;
        this.files = files;
        this.replies = replies;
        this.memberId = memberId;
        this.memberProfilePath = memberProfilePath;
        this.memberProfileUUID = memberProfileUUID;
        this.memberProfileOriginalName = memberProfileOriginalName;
        this.memberNickName = memberNickName;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
        this.eventRecruitCount = eventRecruitCount;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.eventCategory = eventCategory;
    }
}
