package com.app.babybaby.domain.fileDTO.announcementFileDTO;

import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@NoArgsConstructor
public class announcementFileDTOForParameter {
    private List<AnnouncementFileDTO> files = new ArrayList<>();

    @Builder
    public announcementFileDTOForParameter(List<AnnouncementFileDTO> files) {
        this.files = files;
    }
}
