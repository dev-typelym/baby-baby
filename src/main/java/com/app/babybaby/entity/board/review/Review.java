package com.app.babybaby.entity.board.review;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.board.event.Event;
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
@ToString(callSuper = true, exclude = "member")
@Table(name = "TBL_REVIEW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Review extends BoardInfo {

    @NotNull
    @ColumnDefault("0")
    private int ReviewScore;

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

    public Review(int reviewScore, Event event, Member member, List<ReviewReply> reviewReplies, String boardTitle, String boardContent) {
        super(boardTitle, boardContent);
        ReviewScore = reviewScore;
        this.event = event;
        this.member = member;
        this.reviewReplies = reviewReplies;
    }
}
