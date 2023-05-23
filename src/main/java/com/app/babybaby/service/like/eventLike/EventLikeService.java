package com.app.babybaby.service.like.eventLike;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.like.eventLike.EventLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.stream.Collectors;

public interface EventLikeService {
    public Slice<EventLikeDTO> findEventLikeByMemberId(Pageable pageable, Long memberId);

    public void likeSave(Long nowKidsId, Long memberId);

    public void deleteLike(Long nowKidsId, Long memberId);


    default EventLikeDTO EventToDTO(EventLike eventLike){
        return EventLikeDTO.builder()
                .updateDate(eventLike.getEvent().getUpdateDate())
                .registerDate(eventLike.getEvent().getRegisterDate())
                .memberName(eventLike.getMember().getMemberName())
                .eventPrice(eventLike.getEvent().getEventPrice())
                .memberId(eventLike.getMember().getId())
                .boardTitle(eventLike.getEvent().getBoardTitle())
                .id(eventLike.getId())
                .eventRecruitCount(eventLike.getEvent().getEventRecruitCount())
                .eventFileDTOS(eventLike.getEvent().getEventFiles().stream().map(this::eventFileToDTO).collect(Collectors.toList()))
                .eventAddress(eventLike.getEvent().getEventLocation().getAddress())
                .calendar(toCalendarDTO(eventLike.getEvent().getCalendar()))
                .eventId(eventLike.getEvent().getId())
                .build();
    }
//    default EventLikeDTO EventToDTO(Event event){
//        return EventLikeDTO.builder()
//                .updateDate(event.getUpdateDate())
//                .registerDate(event.getRegisterDate())
//                .memberName(event.getCompany().getMemberName())
//                .eventPrice(event.getEventPrice())
//                .memberId(event.getCompany().getId())
//                .boardTitle(event.getBoardTitle())
//                .id(event.getId())
//                .eventRecruitCount(event.getEventRecruitCount())
//                .eventFileDTOS(event.getEventFiles().stream().map(this::eventFileToDTO).collect(Collectors.toList()))
//                .build();
//    }
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
