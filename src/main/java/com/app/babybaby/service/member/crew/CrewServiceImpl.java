package com.app.babybaby.service.member.crew;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.memberDTO.EventKidDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.review.ReviewRepository;
import com.app.babybaby.repository.file.reviewFile.ReviewFileRepository;
import com.app.babybaby.repository.member.crew.CrewRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.purchase.coupon.CouponRepository;
import com.app.babybaby.service.board.event.EventService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class CrewServiceImpl implements CrewService {

    private final CrewRepository crewRepository;

    @Override
    public List<EventKidDTO> findCrewByMemberId(Long sessionId, LocalDate date) {
        List<Tuple> crews = crewRepository.findCrewByMemberId(sessionId,date);
        List<EventKidDTO> eventKidDTOS = null;
        for (Tuple tuple : crews) {
            Event event = tuple.get(0, Event.class);
            Kid kid = tuple.get(1, Kid.class);
            eventKidDTOS = crews.stream().map(crew-> toEventKidDTO(event, kid)).collect(Collectors.toList());
        }
        return eventKidDTOS;
    }
}
