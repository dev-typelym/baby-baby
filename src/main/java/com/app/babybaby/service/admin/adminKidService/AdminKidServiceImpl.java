package com.app.babybaby.service.admin.adminKidService;

import com.app.babybaby.domain.adminDTO.AdminKidDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("kid") @Primary
public class AdminKidServiceImpl implements AdminKidService {

    @Autowired
    NowKidsRepository nowKidsRepository;
    @Autowired
    EventRepository eventRepository;

    @Override
    public List<AdminKidDTO> findAllKidsByGuideIdAndEventId(Long guidId, Long eventId) {
            List<Kid> kids = nowKidsRepository.findAllKidsByEventIdAndGuideId_QueryDsl(guidId, eventId);
            List<AdminKidDTO> kidDTOS = kids.stream().map(kid -> toKidDTO(kid)).collect(Collectors.toList());
            kidDTOS.stream().map(kidDTO -> {
                Event event = eventRepository.findById(eventId).get();
                kidDTO.setEventStartDate(event.getCalendar().getStartDate());
                kidDTO.setEventTitle(event.getBoardTitle());
                return kidDTO;
            }).collect(Collectors.toList());
            return  kidDTOS;
    }
}
