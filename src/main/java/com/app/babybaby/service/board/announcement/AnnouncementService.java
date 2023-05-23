package com.app.babybaby.service.board.announcement;

import com.app.babybaby.domain.boardDTO.announcementDTO.AnnouncementDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.fileDTO.announcementFileDTO.AnnouncementFileDTO;
import com.app.babybaby.domain.fileDTO.parentsBoardFileDTO.ParentsBoardFileDTO;
import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.file.announcementFile.AnnouncementFile;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;

import java.util.List;
import java.util.stream.Collectors;

public interface AnnouncementService {



    //    상세보기 카테고리 최신글 3개 가져오기
    public List<Announcement> find5RecentDesc();

    default AnnouncementDTO toAnnouncementDTO(Announcement announcement) {
        return AnnouncementDTO.DTOBuilderForMain()
                .id(announcement.getId())
                .memberNickname(announcement.getAdmin().getMemberNickname())
                .announcementContent(announcement.getBoardContent())
                .announcementTitle(announcement.getBoardTitle())
                .memberProfileUUID(announcement.getAdmin().getMemberProfileUUID())
                .memberProfileOriginalName(announcement.getAdmin().getMemberProfileOriginalName())
                .memberProfilePath(announcement.getAdmin().getMemberProfilePath())
                .announcementRegisterDate(announcement.getRegisterDate())
                .announcementUpdateDate(announcement.getUpdateDate())
                .announcementFileDTOS(announcement.getAnnouncementFiles().stream()
                        .map(this::announcementFileToDTO).collect(Collectors.toList()))
                .build();
    }


    default AnnouncementFileDTO announcementFileToDTO(AnnouncementFile file) {
        return AnnouncementFileDTO.builder()
                .fileOriginalName(file.getFileOriginalName())
                .filePath(file.getFilePath())
                .fileType(file.getFileStatus())
                .fileUUID(file.getFileUUID())
                .id(file.getId())
                .build();
    }

}
