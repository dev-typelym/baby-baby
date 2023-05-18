package com.app.babybaby.domain.boardDTO.eventDTO;

import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.FileType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
@RequiredArgsConstructor
public class EventDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private Long eventRecruitCount;
    private Address eventLocation;
    private Long eventPrice;
    private CategoryType category;
    private EventBoardSearch eventBoardSearch;
    private CalendarDTO calendar;
    private MemberDTO company;
    private String mainFileOriginalName;
    private String mainFileUUID;
    private String mainFilePath;
    private FileType fileType;
    private List<EventFileDTO> files;

    @Builder
    public EventDTO(Long id, String boardTitle, String boardContent, Long eventRecruitCount, Address eventLocation, Long eventPrice, CategoryType category, EventBoardSearch eventBoardSearch, CalendarDTO calendar, MemberDTO company, String mainFileOriginalName, String mainFileUUID, String mainFilePath, FileType fileType, List<EventFileDTO> files) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.eventRecruitCount = eventRecruitCount;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.category = category;
        this.eventBoardSearch = eventBoardSearch;
        this.calendar = calendar;
        this.company = company;
        this.mainFileOriginalName = mainFileOriginalName;
        this.mainFileUUID = mainFileUUID;
        this.mainFilePath = mainFilePath;
        this.fileType = fileType;
        this.files = files;
    }
    //    private List<EventFileDTO> eventFileDTOS;

//    private CalendarDTO calendarDTO;
}
