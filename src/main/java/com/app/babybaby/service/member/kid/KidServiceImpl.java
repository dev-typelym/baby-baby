package com.app.babybaby.service.member.kid;

import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.member.kid.KidRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
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
@Qualifier("kid") @Primary
public class KidServiceImpl implements KidService {

    private final NowKidsRepository nowKidsRepository;
    private final EventRepository eventRepository;
    private final KidRepository kidRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<KidDTO> findAllKidsByMemberIdAndEventId(Long sessionId, Long eventId) {
        List<Kid> kids = nowKidsRepository.findAllKidsByEventIdAndGuideId_QueryDsl(sessionId, eventId);
        List<KidDTO> kidDTOS = kids.stream().map(kid -> toKidDTO(kid)).collect(Collectors.toList());
        kidDTOS.stream().map(kidDTO -> {
            Event event = eventRepository.findById(eventId).get();
            kidDTO.setEventStartDate(event.getCalendar().getStartDate());
            kidDTO.setEventEndDate(event.getCalendar().getEndDate());
            kidDTO.setEventTitle(event.getBoardTitle());
            return kidDTO;
        }).collect(Collectors.toList());
        return  kidDTOS;
    }

    @Override
    public void save(KidDTO kidDTO){
        Kid kid = toKid(kidDTO);
        kidRepository.save(kid);
    }

}
