package com.app.babybaby.domain.adminDTO;

import com.app.babybaby.domain.fileDTO.parentsBoardFileDTO.ParentsBoardFileDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AdminParentsBoardDTO {
        private Long id;
        private String eventTitle;
        private String memberNickName;
        private String parentsBoardContent;
        private String parensBoardTitle;
        private LocalDateTime parentsBoardRegisterDate;
        private LocalDateTime parentsBoardUpdateDate;
        private List<ParentsBoardFileDTO> parentsBoardFileDTOS;
}
