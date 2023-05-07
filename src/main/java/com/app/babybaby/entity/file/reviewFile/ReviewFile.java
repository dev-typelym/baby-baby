package com.app.babybaby.entity.file.reviewFile;

import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.file.File;
import com.app.babybaby.type.FileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"review"})
@Table(name = "TBL_REVIEW_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID")
    private Review review;

    public ReviewFile(Review review) {
        this.review = review;
    }

    public ReviewFile(String fileOriginalName, String fileUUID, String filePath, FileType fileStatus, Review review) {
        super(fileOriginalName, fileUUID, filePath, fileStatus);
        this.review = review;
    }
}
