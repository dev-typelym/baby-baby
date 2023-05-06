package com.app.babybaby.entity.reply;

import com.app.babybaby.entity.audit.Period;
import com.app.babybaby.entity.board.ParentsBoard;
import com.app.babybaby.entity.board.Review;
import com.app.babybaby.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = {"review", "user"})
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
    @JoinColumn(name = "USER_ID")
    private User user;

}
