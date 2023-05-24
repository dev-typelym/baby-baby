package com.app.babybaby.service.admin.adminAnnouncement;

import com.app.babybaby.domain.adminDTO.AdminAnnouncementDTO;
import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.file.announcementFile.AnnouncementFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.announcement.AnnouncementRepository;
import com.app.babybaby.repository.file.announcementFile.AnnouncementFileRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.search.admin.AdminAnnouncementSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class AdminAnnouncementServiceImpl implements AdminAnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private AnnouncementFileRepository announcementFileRepository;

    @Autowired
    private MemberRepository memberRepository;

//    관리자 공지사항 목록
    @Override
    public Page<AdminAnnouncementDTO> getAdminAnnouncementListWithPaging(int page, AdminAnnouncementSearch adminAnnouncementSearch) {
        Page<Announcement> announcements = announcementRepository.findAllAnnouncement_queryDSL(PageRequest.of(page, 5), adminAnnouncementSearch);
        List<AdminAnnouncementDTO> adminAnnouncementDTOS = announcements.getContent().stream()
                .map(this::toAnnouncementDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(adminAnnouncementDTOS, announcements.getPageable(), announcements.getTotalElements());
    }

//    관리자 공지사항 상세
    @Override
    public List<AdminAnnouncementDTO> getAdminAnnouncementDetail() {
        List<Announcement> announcementDetail = announcementRepository.findAllAnnouncementDetail_queryDSL();
        List<AdminAnnouncementDTO> adminAnnouncementDetailDTOS = announcementDetail.stream()
                .map(this::toAnnouncementDTO)
                .collect(Collectors.toList());
        return adminAnnouncementDetailDTOS;
    }

//    관리자 공지사항 삭제
    @Override
    public void deleteAdminAnnouncemnet(List<String> announcementIds) {
        announcementIds.stream().map(announcementId -> Long.parseLong(announcementId)).forEach(announcementFileRepository::deleteByAnnouncementId);
        announcementIds.stream().map(announcementId -> Long.parseLong(announcementId)).forEach(announcementRepository::deleteAnnouncementByIds_queryDSL);
    }

//    관리자 공지사항 등록
    @Override
    @Transactional
    public void announcementWrite(AdminAnnouncementDTO adminAnnouncementDTO) {
        Long sessionId = 1L;
        Member member = memberRepository.findById(sessionId).get();
        Announcement announcement = toAnnouncementEntity(adminAnnouncementDTO);
        announcementRepository.save(announcement);
        List<AnnouncementFile> announcementFiles = toAnnouncementEntity(adminAnnouncementDTO).getAnnouncementFiles();
        announcementFiles.forEach(announcementFile -> announcementFile.setAnnouncement(announcement));
        announcementFileRepository.saveAll(announcementFiles);
    }

}
