package com.app.babybaby.service.admin.adminAnnouncement;

import com.app.babybaby.domain.adminDTO.AdminAnnouncementDTO;
import com.app.babybaby.domain.fileDTO.announcementFileDTO.AnnouncementFileDTO;
import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.file.announcementFile.AnnouncementFile;
import com.app.babybaby.search.admin.AdminAnnouncementSearch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface AdminAnnouncementService {

    //    관리자 공지사항 목록
    public Page<AdminAnnouncementDTO> getAdminAnnouncementListWithPaging(int page, AdminAnnouncementSearch adminAnnouncementSearch);

    //    관리자 공지사항 상세 보기
    public List<AdminAnnouncementDTO> getAdminAnnouncementDetail();

    //    관리자 공지사항 삭제하기
    public void deleteAdminAnnouncemnet(List<String> announcementIds);

    //    관리자 공지사항 등록하기
    public void announcementWrite(AdminAnnouncementDTO adminAnnouncementDTO);

//    default AdminAnnouncementDTO toAnnouncementDTO(Announcement announcement){
//
//
//        return AdminAnnouncementDTO.builder()
//                .id(announcement.getId())
//                .announcementTitle(announcement.getBoardTitle())
//                .announcementContent(announcement.getBoardContent())
//                .writeDate(announcement.getRegisterDate())
//                .writerName(announcement.getAdmin().getMemberName())
//                .announcementFileDTOS(announcement.getAnnouncementFiles().stream().map(announcementFile -> announcementFileToDTO(announcementFile)).collect(Collectors.toList()))
//                .build();
//    }
//
//
//
//    default AnnouncementFileDTO announcementFileToDTO(AnnouncementFile announcementFile) {
//        return AnnouncementFileDTO.builder()
//                .fileOriginalName(announcementFile.getFileOriginalName())
//                .filePath(announcementFile.getFilePath())
//                .fileStatus(announcementFile.getFileStatus())
//                .fileUUID(announcementFile.getFileUUID())
//                .build();
//    }

//  공지사항 DTO로 바꾸기
    default AdminAnnouncementDTO toAnnouncementDTO(Announcement announcement){


        return AdminAnnouncementDTO.builder()
                .id(announcement.getId())
                .announcementTitle(announcement.getBoardTitle())
                .announcementContent(announcement.getBoardContent())
                .writeDate(announcement.getRegisterDate())
                .writerName(announcement.getAdmin().getMemberName())
                .announcementFileDTOS(toAnnouncementFileDTO(announcement.getAnnouncementFiles()))
                .build();
    }



    default List<AnnouncementFileDTO> toAnnouncementFileDTO(List<AnnouncementFile> announcementFiles) {
        return announcementFiles.stream()
                .map(announcementFile -> AnnouncementFileDTO.builder()
                    .fileOriginalName(announcementFile.getFileOriginalName())
                    .filePath(announcementFile.getFilePath())
                    .fileType(announcementFile.getFileStatus())
                    .fileUUID(announcementFile.getFileUUID())
                    .build())
                .collect(Collectors.toList());
    }



    //    공지사항 DTO Entity로 바꾸기
    default Announcement toAnnouncementEntity(AdminAnnouncementDTO adminAnnouncementDTO) {
        return Announcement.builder()
                .id(adminAnnouncementDTO.getId())
                .announcementTitle(adminAnnouncementDTO.getAnnouncementTitle())
                .announcementContent(adminAnnouncementDTO.getAnnouncementContent())
                .announcementFiles(adminAnnouncementDTO.getAnnouncementFileDTOS().stream()
                        .map(this::toAnnouncementFileEntity)
                        .collect(Collectors.toList())
                )
                .build();
    }

    //    공지사항 파일 DTO Entity로 바꾸기
    default AnnouncementFile toAnnouncementFileEntity(AnnouncementFileDTO announcementFileDTO) {
        return AnnouncementFile.builder()
                .filePath(announcementFileDTO.getFilePath())
                .fileUUID(announcementFileDTO.getFileUUID())
                .fileOriginalName(announcementFileDTO.getFileOriginalName())
                .build();
    }
}
