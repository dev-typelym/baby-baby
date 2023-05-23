package com.app.babybaby.service.guideSchedule;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.like.eventLike.EventLike;

import java.util.List;
import java.util.stream.Collectors;

public interface GuideScheduleService {
    public List<EventDTO> findAllEventsByMemberId_QueryDsl(Long memberId);




    default EventDTO eventToDTO(Event event){
        return EventDTO.builder()
                .id(event.getId())
                .boardContent(event.getBoardContent())
                .boardTitle(event.getBoardTitle())
                .category(event.getCategory())
                .eventLocation(event.getEventLocation())
                .eventPrice(event.getEventPrice())
                .eventRecruitCount(event.getEventRecruitCount())
                .calendar(toCalendarDTO(event.getCalendar()))
                .files(event.getEventFiles().stream().map(eventFile -> eventFileToDTO(eventFile)).collect(Collectors.toList()))
                .build();
    }
    default EventDTO eventToDTOS(Event event){
        return EventDTO.builder()
                .id(event.getId())
                .boardContent(event.getBoardContent())
                .boardTitle(event.getBoardTitle())
                .category(event.getCategory())
                .eventLocation(event.getEventLocation())
                .eventPrice(event.getEventPrice())
                .eventRecruitCount(event.getEventRecruitCount())
                .calendar(toCalendarDTO(event.getCalendar()))
                .files(event.getEventFiles().stream().map(eventFile -> eventFileToDTO(eventFile)).collect(Collectors.toList()))
                .build();
    }

    default Event toEventEntity(EventDTO eventDTO){
        return Event.builder()
                .id(eventDTO.getId())
                .category(eventDTO.getCategory())
                .eventLocation(eventDTO.getEventLocation())
                .eventRecruitCount(eventDTO.getEventRecruitCount())
                .eventPrice(eventDTO.getEventPrice())
                .boardContent(eventDTO.getBoardContent())
                .boardTitle(eventDTO.getBoardTitle())
                .eventFiles(eventDTO.getFiles().stream().map(this::toEventFileEntity).collect(Collectors.toList()))
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

    default Calendar toCalendarEntity(CalendarDTO calendarDTO){
        return Calendar.builder()
                .calendarName(calendarDTO.getCalendarName())
                .endDate(calendarDTO.getEndDate())
                .startDate(calendarDTO.getStartDate())
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

    default EventFile toEventFileEntity(EventFileDTO eventFileDTO){
        return EventFile.builder()
                .fileOriginalName(eventFileDTO.getFileOriginalName())
                .filePath(eventFileDTO.getFilePath())
                .fileStatus(eventFileDTO.getFileStatus())
                .fileUUID(eventFileDTO.getFileUUID())
                .build();
    }

}
