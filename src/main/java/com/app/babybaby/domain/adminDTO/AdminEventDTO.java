package com.app.babybaby.domain.adminDTO;

import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.type.CategoryType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AdminEventDTO {

    private Long id;
    private String memberName;
    private CategoryType category;
    private String boardTitle;
    private String eventAddress;
    private String eventAddressDetail;
    private String eventAddressSubDetail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long eventRecruitCount;
    private List<EventFileDTO> eventFileDTOS;
}
