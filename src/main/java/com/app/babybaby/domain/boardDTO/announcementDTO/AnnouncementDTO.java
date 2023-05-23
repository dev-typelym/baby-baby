package com.app.babybaby.domain.boardDTO.announcementDTO;

import com.app.babybaby.domain.fileDTO.announcementFileDTO.AnnouncementFileDTO;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class AnnouncementDTO {
    private Long id;
    private String memberNickname;
    private String announcementContent;
    private String announcementTitle;
    private String memberProfileUUID;
    private String memberProfilePath;
    private String memberProfileOriginalName;
//    private String announcementFileUUID;
//    private String announcementFilePath;
//    private String announcementFileOriginalName;
    private List<AnnouncementFileDTO> announcementFileDTOS;
    private LocalDateTime announcementRegisterDate;
    private LocalDateTime announcementUpdateDate;

    @Builder(builderMethodName = "DTOBuilderForMain", builderClassName = "DTOBuilderForMain")
    public AnnouncementDTO(Long id, String memberNickname, String announcementContent, String announcementTitle, String memberProfileUUID, String memberProfilePath, String memberProfileOriginalName, List<AnnouncementFileDTO> announcementFileDTOS, LocalDateTime announcementRegisterDate, LocalDateTime announcementUpdateDate) {
        this.id = id;
        this.memberNickname = memberNickname;
        this.announcementContent = announcementContent;
        this.announcementTitle = announcementTitle;
        this.memberProfileUUID = memberProfileUUID;
        this.memberProfilePath = memberProfilePath;
        this.memberProfileOriginalName = memberProfileOriginalName;
        this.announcementFileDTOS = announcementFileDTOS;
        this.announcementRegisterDate = announcementRegisterDate;
        this.announcementUpdateDate = announcementUpdateDate;
    }
}
