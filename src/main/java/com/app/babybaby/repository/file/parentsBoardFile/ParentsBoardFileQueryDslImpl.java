package com.app.babybaby.repository.file.parentsBoardFile;

import com.app.babybaby.entity.file.eventFile.QEventFile;
import com.app.babybaby.entity.file.parentsBoardFile.QParentsBoardFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

import static com.app.babybaby.entity.file.nowKidsFile.QNowKidsFile.nowKidsFile;

@RequiredArgsConstructor
public class ParentsBoardFileQueryDslImpl implements ParentsBoardFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    @Transactional
    public void deleteByParentsBoardId(Long parentsBoardId) {
        QParentsBoardFile parentsBoardFile = QParentsBoardFile.parentsBoardFile;
        query.delete(parentsBoardFile)
                .where(parentsBoardFile.parentsBoard.id.eq(parentsBoardId))
                .execute();
    }
}
