package com.app.babybaby.repository.board.announcement;

import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.search.admin.AdminAnnouncementSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AnnouncementQueryDsl {
    //    [관리자] 공지사항 목록 조회
    public Page<Announcement> findAllAnnouncement_queryDSL(Pageable pageable, AdminAnnouncementSearch adminAnnouncementSearch);

    //    [관리자] 공지사항 상세보기
    public Optional<Announcement> findAnnouncementById_queryDSL(Long announcementId);

    //    [관리자] 공지사항 삭제하기
    public void deleteAnnouncementByIds_queryDSL(List<Long> announcementIds);

    //    공지사항 최신글 3개 가져오기
    public List<Announcement> find5RecentDesc();


}
