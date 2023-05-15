package com.app.babybaby.domain.fileDTO.nowKidsFileDTO;

import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@NoArgsConstructor
public class NowKidsFileDTOForParameter {
    private List<NowKidsFileDTO> files = new ArrayList<>();

    @Builder
    public NowKidsFileDTOForParameter(List<NowKidsFileDTO> files) {
        this.files = files;
    }
}
