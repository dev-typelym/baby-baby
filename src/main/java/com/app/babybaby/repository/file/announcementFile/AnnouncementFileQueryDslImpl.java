package com.app.babybaby.repository.file.announcementFile;

import com.app.babybaby.entity.board.announcement.QAnnouncement;
import com.app.babybaby.entity.board.nowKids.QNowKids;
import com.app.babybaby.entity.file.announcementFile.AnnouncementFile;
import com.app.babybaby.entity.file.announcementFile.QAnnouncementFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

import static com.app.babybaby.entity.board.nowKids.QNowKids.nowKids;
import static com.app.babybaby.entity.file.announcementFile.QAnnouncementFile.announcementFile;
@RequiredArgsConstructor
public class AnnouncementFileQueryDslImpl implements AnnouncementFileQueryDsl {

    private final JPAQueryFactory query;

    @Override
    @Transactional
    public void deleteByAnnouncementId(Long announcementId) {
        QAnnouncementFile qAnnouncementFile =  announcementFile;
        query.delete(announcementFile)
                .where(announcementFile.announcement.id.eq(announcementId))
                .execute();
    }
}
