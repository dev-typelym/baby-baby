package com.app.babybaby.service.board.event;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.file.eventFile.EventFileRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.service.member.member.MemberService;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final MemberRepository memberRepository;

    private final EventFileRepository eventFileRepository;


    @Override
    public Slice<EventDTO> findEventListWithPaging(EventBoardSearch eventBoardSearch, Pageable pageable) {
        Slice<Event> events = eventRepository.findEventListWithPaging_QueryDSL(eventBoardSearch, pageable);
        events.get().map(event -> event.toString()).forEach(log::info);

        List<EventDTO> collect = events.get().map(event -> eventToDTO(event)).collect(Collectors.toList());
//        List<EventDTO> collect = events.get().collect(Collectors.toList());
        return new SliceImpl<>(collect,pageable,events.hasNext());
    }

    public void saveAll(Long memberId, EventDTO eventDTO, Calendar calendar){
        Member member = memberRepository.findById(memberId).get();
        eventDTO.setCompany(this.memberToDTO(member));
        eventDTO.setCalendar(this.toCalendarDTO(calendar));
        log.info("내가 가져온 맴버 : " + member);
        log.info("내가 가져온 Calendar : " + calendar);
        log.info(eventDTO.toString());
        Event event = this.toEventEntity(eventDTO);
        eventRepository.save(event);
        log.info("엔티티로 바뀐 eventFile은 " + event.getEventFiles().toString());
        log.info("엔티티로 바뀐 member는 : "+ event.getCompany());

        event.getEventFiles().stream().map(eventFile -> eventFileRepository.save(eventFile));

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

}
