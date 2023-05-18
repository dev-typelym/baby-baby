package com.app.babybaby.entity.reply.reviewReply;

import com.app.babybaby.entity.audit.Period;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = {"review", "member"})
@Table(name = "TBL_REVIEW_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewReply extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String ReviewReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public ReviewReply(Long id, String reviewReplyContent, Review review, Member member) {
        this.id = id;
        ReviewReplyContent = reviewReplyContent;
        this.review = review;
        this.member = member;
    }
}
