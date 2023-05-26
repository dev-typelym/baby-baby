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


    @Override
    public List<Announcement> find5RecentDesc() {
        List<Announcement> announcements =
                query.select(announcement)
                        .from(announcement)
                        .orderBy(announcement.id.desc())
                        .limit(3)
                        .fetch();
        return announcements;
    }

    //      [관리자] 공지사항 목록 조회
    @Override
    public Page<Announcement> findAllAnnouncement_queryDSL(Pageable pageable, AdminAnnouncementSearch adminAnnouncementSearch) {
        BooleanExpression announcementTitleEq = adminAnnouncementSearch.getAnnouncementTitle() == null ? null : announcement.boardTitle.like("%" + adminAnnouncementSearch.getAnnouncementTitle()    + "%");

        QAnnouncement announcement = QAnnouncement.announcement;
        List<Announcement> foundAnnouncement = query.select(announcement)
                .from(announcement)
                .where(announcementTitleEq)
                .orderBy(announcement.id.desc())
                .offset(pageable.getOffset())
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
    public List<Announcement> findAllAnnouncementDetail_queryDSL() {
        List<Announcement> foundAnnouncementDetail =
                query.select(announcement)
                        .from(announcement)
                        .leftJoin(announcement.announcementFiles)
                        .fetchJoin()
                        .fetch();
        return foundAnnouncementDetail;
    }


    //    [관리자] 공지사항 삭제
    @Override
    public void deleteAnnouncementByIds_queryDSL(Long announcementId) {
        query.delete(announcement)
                .where(announcement.id.in(announcementId))
                .execute();
    }

}
