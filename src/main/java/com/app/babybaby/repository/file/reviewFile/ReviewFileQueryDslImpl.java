package com.app.babybaby.repository.file.reviewFile;

import com.app.babybaby.entity.file.parentsBoardFile.QParentsBoardFile;
import com.app.babybaby.entity.file.reviewFile.QReviewFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
@RequiredArgsConstructor
public class ReviewFileQueryDslImpl implements ReviewFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    @Transactional
    public void deleteByReviewBoardId(Long reviewBoardId) {
        QReviewFile reviewFile = QReviewFile.reviewFile;
        query.delete(reviewFile)
                .where(reviewFile.review.id.eq(reviewBoardId))
                .execute();
    }
}
