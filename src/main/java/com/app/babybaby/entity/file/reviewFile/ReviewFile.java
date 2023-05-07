package com.app.babybaby.entity.file.reviewFile;

import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.file.File;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"review"})
@Table(name = "TBL_REVIEW_FILE")
@PrimaryKeyJoinColumn(name = "ID")
public class ReviewFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID")
    private Review review;
}
