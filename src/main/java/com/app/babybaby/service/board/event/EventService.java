package com.app.babybaby.service.board.event;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface EventService {
//    이벤트 게시판 리스트
    Slice<EventDTO> findEventListWithPaging(Long sessionId, EventBoardSearch eventBoardSearch, Pageable pageable);
//    내가 쓴 이벤트 게시판
    Slice<EventDTO> findMemberIdByEventListWithPaging(Long memberId, Pageable pageable);

    public void saveAll(Long memberId, EventDTO eventDTO, Calendar calendar);
    
    /* 이벤트 상세보기 */
    public EventDTO getAllEventInfo(Long sessionId, Long eventId);

    Event createEvent(Event event);

    Event updateEvent(Long eventId, Event updatedEvent);

    void deleteEvent(Long eventId);

    default EventDTO eventToDTO(Event event){
        return EventDTO.builder()
                .id(event.getId())
                .boardContent(event.getBoardContent())
                .boardTitle(event.getBoardTitle())
                .category(event.getCategory())
                .eventLocation(event.getEventLocation())
                .eventPrice(event.getEventPrice())
                .eventRecruitCount(event.getEventRecruitCount())
                .memberId(event.getCompany().getId())
                .memberEmail(event.getCompany().getMemberEmail())
                .memberPhone(event.getCompany().getMemberPhone())
                .memberHiSentence(event.getCompany().getMemberHiSentence())
                .memberName(event.getCompany().getMemberName())
                .memberNickname(event.getCompany().getMemberNickname())
                .memberLocation(event.getCompany().getMemberAddress())
                .memberProfileOriginalName(event.getCompany().getMemberProfileOriginalName())
                .memberProfilePath(event.getCompany().getMemberProfilePath())
                .memberProfileUUID(event.getCompany().getMemberProfileUUID())
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
//                    .company(memberDTOToEntity(eventDTO.getCompany()))
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

    default MemberDTO memberToDTO(Member Member){
        return MemberDTO.DTOBuilder()
                .memberAddress(Member.getMemberAddress())
                .memberEmail(Member.getMemberEmail())
                .memberFileOriginalName(Member.getMemberFileOriginalName())
                .memberFilePath(Member.getMemberFilePath())
                .memberFileUUID(Member.getMemberFileUUID())
                .memberGuideInterest(Member.getMemberGuideInterest())
                .memberGuideStatus(Member.getMemberGuideStatus())
                .memberGuideType(Member.getMemberGuideType())
                .memberHiSentence(Member.getMemberHiSentence())
                .alerts(Member.getAlerts())
                .coupons(Member.getCoupons())
                .memberName(Member.getMemberName())
                .memberNickname(Member.getMemberNickname())
                .memberPassword(Member.getMemberPassword())
                .memberPhone(Member.getMemberPhone())
                .memberProfileOriginalName(Member.getMemberProfileOriginalName())
                .memberProfilePath(Member.getMemberProfilePath())
                .memberProfileUUID(Member.getMemberProfileUUID())
                .memberRegisterDate(Member.getMemberRegisterDate())
                .memberRole(Member.getMemberRole())
                .memberSleep(Member.getMemberSleep())
                .memberType(Member.getMemberType())
                .id(Member.getId())
                .build();
    }


    default Member memberDTOToEntity(MemberDTO memberDTO) {
        return Member.joinMemberBuilder()
                .id(memberDTO.getId())
                .memberName(memberDTO.getMemberName())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberNickname(memberDTO.getMemberNickname())
                .memberPhone(memberDTO.getMemberPhone())
                .memberAddress(memberDTO.getMemberAddress())
                .memberProfileOriginalName(memberDTO.getMemberProfileOriginalName())
                .memberProfilePath(memberDTO.getMemberFilePath())
                .memberRegisterDate(memberDTO.getMemberRegisterDate())
                .memberType(memberDTO.getMemberType())
                .memberRole(memberDTO.getMemberRole())
                .build();
    }


}
