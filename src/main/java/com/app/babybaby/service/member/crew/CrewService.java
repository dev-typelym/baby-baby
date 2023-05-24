package com.app.babybaby.service.member.crew;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.EventKidDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.member.Kid;
import com.querydsl.core.Tuple;

import java.util.List;
import java.util.stream.Collectors;

public interface CrewService {
    public List<EventKidDTO> findCrewByMemberId(Long sessionId, String date);




    default EventKidDTO toEventKidDTO(Event event,Kid kid) {
        return EventKidDTO.builder()
                .boardContent(event.getBoardContent())
                .boardTitle(event.getBoardTitle())
                .calendarDTO(toCalendarDTO(event.getCalendar()))
                .category(event.getCategory())
                .eventId(event.getId())
                .eventLocation(event.getEventLocation())
                .eventPrice(event.getEventPrice())
                .eventRecruitCount(event.getEventRecruitCount())
                .files(event.getEventFiles().stream().map(eventFile -> eventFileToDTO(eventFile)).collect(Collectors.toList()))
                .kidAge(kid.getKidAge())
                .kidGender(kid.getKidGender())
                .kidId(kid.getId())
                .kidName(kid.getKidName())
                .build();
    }

    default CalendarDTO toCalendarDTO(Calendar calendar){
        return CalendarDTO.builder()
                .id(calendar.getId())
                .calendarName(calendar.getCalendarName())
                .endDate(calendar.getEndDate())
                .startDate(calendar.getStartDate())
                .id(calendar.getId())
                .build();
    }

    default EventFileDTO eventFileToDTO(EventFile eventFile){
        return EventFileDTO.builder()
                .id(eventFile.getId())
                .fileOriginalName(eventFile.getFileOriginalName())
                .filePath(eventFile.getFilePath())
                .fileStatus(eventFile.getFileStatus())
                .fileUUID(eventFile.getFileUUID())
                .build();
    }


}
