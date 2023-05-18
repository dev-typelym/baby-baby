package com.app.babybaby.service.like.eventLike;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.like.eventLike.EventLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.stream.Collectors;

public interface EventLikeService {
    public Slice<EventLikeDTO> findEventLikeByMemberId(Pageable pageable, Long memberId);



    default EventLikeDTO EventToDTO(Event event){
        return EventLikeDTO.builder()
                .updateDate(event.getUpdateDate())
                .registerDate(event.getRegisterDate())
                .memberName(event.getCompany().getMemberName())
                .eventPrice(event.getEventPrice())
                .memberId(event.getCompany().getId())
                .boardTitle(event.getBoardTitle())
                .id(event.getId())
                .eventRecruitCount(event.getEventRecruitCount())
                .eventFileDTOS(event.getEventFiles().stream().map(this::eventFileToDTO).collect(Collectors.toList()))
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
