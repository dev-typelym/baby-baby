package com.app.babybaby.repository.file.eventFile;

import com.app.babybaby.entity.file.announcementFile.QAnnouncementFile;
import com.app.babybaby.entity.file.eventFile.QEventFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

import static com.app.babybaby.entity.file.announcementFile.QAnnouncementFile.announcementFile;
import static com.app.babybaby.entity.file.eventFile.QEventFile.eventFile;

@RequiredArgsConstructor
public class EventFileQueryDslImpl implements EventFileQueryDsl {

    private final JPAQueryFactory query;

    @Override
    @Transactional
    public void deleteByEventId(Long eventId) {
        QEventFile eventFile = QEventFile.eventFile;
        query.delete(eventFile)
                .where(eventFile.event.id.eq(eventId))
                .execute();
    }
}
