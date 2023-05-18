package com.app.babybaby.entity.board.review;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.reviewFile.ReviewFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.reply.reviewReply.ReviewReply;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"member", "event", "ReviewReplies"})
@Table(name = "TBL_REVIEW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Review extends BoardInfo {

    @NotNull
    @ColumnDefault("0")
    private int ReviewScore;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewFile> reviewFiles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    private List<ReviewReply> reviewReplies;

    public void addReviewReply(ReviewReply reviewReply){
        this.reviewReplies.add(reviewReply);
    }

    @Builder
    public Review(String boardTitle, String boardContent, int reviewScore, List<ReviewFile> reviewFiles, Event event, Member member) {
        super(boardTitle, boardContent);
        ReviewScore = reviewScore;
        this.reviewFiles = reviewFiles;
        this.event = event;
        this.member = member;
    }
}
