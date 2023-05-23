package com.app.babybaby.service.guideSchedule;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.guideSchedule.GuideScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class GuideScheduleServiceImpl implements GuideScheduleService {

    private final EventRepository eventRepository;
    private final GuideScheduleRepository guideScheduleRepository;

//    일단 이거 이벤트 스케쥴 목록 조회
    @Override
    public List<EventDTO> findAllEventsByMemberId_QueryDsl(Long memberId) {
        List<Event> events = guideScheduleRepository.findAllEventsByMemberId_QueryDsl(memberId);
        List<EventDTO> eventDTOS = events.stream().map(event -> eventToDTO(event)).collect(Collectors.toList());
        return eventDTOS;
    }


}
