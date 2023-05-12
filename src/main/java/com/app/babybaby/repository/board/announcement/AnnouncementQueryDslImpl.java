package com.app.babybaby.repository.board.announcement;

import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.board.announcement.QAnnouncement;
import com.app.babybaby.search.admin.AdminAnnouncementSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.announcement.QAnnouncement.announcement;

@RequiredArgsConstructor
public class AnnouncementQueryDslImpl implements AnnouncementQueryDsl {

    private final JPAQueryFactory query;

    //      [관리자] 공지사항 목록 조회
    @Override
    public Page<Announcement> findAllAnnouncement_queryDSL(Pageable pageable, AdminAnnouncementSearch adminAnnouncementSearch) {
        BooleanExpression announcementTitleEq = adminAnnouncementSearch.getAnnouncementTitle() == null ? null : announcement.boardTitle.eq(adminAnnouncementSearch.getAnnouncementTitle());

        QAnnouncement announcement = QAnnouncement.announcement;
        List<Announcement> foundAnnouncement = query.select(announcement)
                .from(announcement)
                .where(announcementTitleEq)
                .orderBy(announcement.id.asc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(announcement.count())
                .from(announcement)
                .where(announcementTitleEq)
                .fetchOne();

        return new PageImpl<>(foundAnnouncement, pageable, count);
    }

    //    [관리자] 공지사항 상세보기
    @Override
    public Optional<Announcement> findAnnouncementById_queryDSL(Long announcementId) {
        return Optional.ofNullable(
                query.select(announcement)
                        .from(announcement)
                        .leftJoin(announcement.announcementFiles)
                        .fetchJoin()
                        .where(announcement.id.eq(announcementId))
                        .fetchOne());
    }


    //    [관리자] 공지사항 삭제
    @Override
    public void deleteAnnouncementByIds_queryDSL(List<Long> announcementIds) {
        query.delete(announcement)
                .where(announcement.id.in(announcementIds))
                .execute();
    }
}
