package com.app.babybaby.service.board.event;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;


    @Override
    public Slice<EventDTO> findEventListWithPaging(EventBoardSearch eventBoardSearch, Pageable pageable) {
        Slice<Event> events = eventRepository.findEventListWithPaging_QueryDSL(eventBoardSearch, pageable);
        List<EventDTO> collect = events.get().map(event -> eventToDTO(event)).collect(Collectors.toList());
        return new SliceImpl<>(collect,pageable,events.hasNext());
    }


    @Override
    public Event createEvent(Event event) {
        return null;
    }

    @Override
    public Event updateEvent(Long eventId, Event updatedEvent) {
        return null;
    }

    @Override
    public void deleteEvent(Long eventId) {

    }

    @Override
    public EventDTO eventToDTO(Event event) {
        return null;
    }

    @Override
    public EventFileDTO eventFileToDTO(EventFile eventFile) {
        return null;
    }

    @Override
    public MemberDTO memberToDTO(Member Member) {
        return null;
    }

}
