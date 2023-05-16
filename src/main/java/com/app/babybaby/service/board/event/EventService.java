package com.app.babybaby.service.board.event;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
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
    Slice<EventDTO> findEventListWithPaging(EventBoardSearch eventBoardSearch, Pageable pageable);

    Event createEvent(Event event);

    Event updateEvent(Long eventId, Event updatedEvent);

    void deleteEvent(Long eventId);

    default EventDTO eventToDTO(Event event){
        return EventDTO.builder()
                .id(event.getId())
                .company(memberToDTO(event.getCompany()))
                .boardContent(event.getBoardContent())
                .boardTitle(event.getBoardTitle())
                .category(event.getCategory())
                .eventLocation(event.getEventLocation())
                .eventPrice(event.getEventPrice())
                .eventRecruitCount(event.getEventRecruitCount())
                .eventFileDTOS(event.getEventFiles().stream().map(eventFile -> eventFileToDTO(eventFile)).collect(Collectors.toList()))
                .build();
        }

    default EventFileDTO eventFileToDTO(EventFile eventFile){
        return EventFileDTO.builder()
                .fileOriginalName(eventFile.getFileOriginalName())
                .filePath(eventFile.getFilePath())
                .fileStatus(eventFile.getFileStatus())
                .fileUUID(eventFile.getFileUUID())
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

}
