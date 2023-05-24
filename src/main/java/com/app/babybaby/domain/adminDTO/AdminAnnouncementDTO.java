package com.app.babybaby.domain.adminDTO;

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
public class AdminAnnouncementDTO {
    private Long id;
    private String announcementTitle;
    private String writerName;
    private LocalDateTime writeDate;
    private String announcementContent;
    private List<AnnouncementFileDTO> announcementFileDTOS;

//    @JsonIgnore
//    private List<AnnouncementFileDTO> files;

@Builder
    public AdminAnnouncementDTO(Long id, String announcementTitle, String writerName, LocalDateTime writeDate, String announcementContent, List<AnnouncementFileDTO> announcementFileDTOS/*, List<AnnouncementFileDTO> files*/) {
        this.id = id;
        this.announcementTitle = announcementTitle;
        this.writerName = writerName;
        this.writeDate = writeDate;
        this.announcementContent = announcementContent;
        this.announcementFileDTOS = announcementFileDTOS;
//        this.files = files;
    }
}
