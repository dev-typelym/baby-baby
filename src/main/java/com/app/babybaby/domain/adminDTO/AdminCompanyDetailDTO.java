package com.app.babybaby.domain.adminDTO;

import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.type.CategoryType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class AdminCompanyDetailDTO {
    private Long id;
    private String boardTitle;
    private String memberName;
    private CategoryType category;
    private Address eventLocation;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long eventRecruitCount;
    private String profileOriginalName;
    private String profileUUID;
    private String profilePath;


    @Builder
    public AdminCompanyDetailDTO(Long id, String memberName, String boardTitle, CategoryType category, Address eventLocation, LocalDateTime startDate, LocalDateTime endDate, Long eventRecruitCount, String profileOriginalName, String profileUUID, String profilePath) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.memberName = memberName;
        this.category = category;
        this.eventLocation = eventLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventRecruitCount = eventRecruitCount;
        this.profileOriginalName = profileOriginalName;
        this.profileUUID = profileUUID;
        this.profilePath = profilePath;
    }
}
