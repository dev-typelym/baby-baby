package com.app.babybaby.domain.boardDTO.eventDTO;

import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.FileType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Getter
@Setter
@ToString(exclude = {"company", "calendar"})
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
//    private MemberDTO company;
    private Long memberId;
    private String memberName;
    private String memberNickname;
    private String mainFileOriginalName;
    private String mainFileUUID;
    private String mainFilePath;
    private FileType fileType;
    private List<EventFileDTO> files;



    @Builder
    public EventDTO(Long id, String boardTitle, String boardContent, Long eventRecruitCount, Address eventLocation, Long eventPrice, CategoryType category, EventBoardSearch eventBoardSearch, CalendarDTO calendar, Long memberId, String memberName, String memberNickname, String mainFileOriginalName, String mainFileUUID, String mainFilePath, FileType fileType, List<EventFileDTO> files) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.eventRecruitCount = eventRecruitCount;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.category = category;
        this.eventBoardSearch = eventBoardSearch;
        this.calendar = calendar;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.mainFileOriginalName = mainFileOriginalName;
        this.mainFileUUID = mainFileUUID;
        this.mainFilePath = mainFilePath;
        this.fileType = fileType;
        this.files = files;
    }
    //    private List<EventFileDTO> eventFileDTOS;

//    private CalendarDTO calendarDTO;
}
