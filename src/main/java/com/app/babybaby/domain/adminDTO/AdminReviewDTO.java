package com.app.babybaby.domain.adminDTO;

import com.app.babybaby.domain.fileDTO.reviewFileDTO.ReviewFileDTO;
import com.app.babybaby.type.CategoryType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AdminReviewDTO {
    private Long id;
    private CategoryType eventCategory;
    private String eventTitle;
    private String nickName;
    private LocalDateTime startDate;
    private Long eventRecruitCount;
    private LocalDateTime writeDate;
    private int reviewScore;
    private String guideName;
    private String reviewTitle;
    private String reviewContent;
    private List<ReviewFileDTO> reviewFileDTOS;
}
