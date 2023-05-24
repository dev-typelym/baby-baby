package com.app.babybaby.domain.adminDTO;

import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.type.CategoryType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AdminNowKidsDTO {
    private Long id;
    private CategoryType category;
    private String boardTitle;
    private String guideName;
    private Long guideId;
    private Long eventId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String eventAddress;
    private String eventAddressDetail;
    private String eventAddressSubDetail;
    private String kidName;
    private String nickName;
    private Integer kidAge;
    private List<NowKidsFileDTO> nowKidsFileDTOS;
    private List<AdminKidDTO> adminKidDTOS;
}
