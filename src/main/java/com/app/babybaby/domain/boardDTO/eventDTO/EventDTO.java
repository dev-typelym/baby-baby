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
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
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
    private FileType fileType;

    private List<EventFileDTO> files;
//    private List<EventFileDTO> eventFileDTOS;

//    private CalendarDTO calendarDTO;
}
