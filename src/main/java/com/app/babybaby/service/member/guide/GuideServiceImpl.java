package com.app.babybaby.service.member.guide;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Crew;
import com.app.babybaby.entity.member.Guide;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.member.crew.CrewRepository;
import com.app.babybaby.repository.member.guide.GuidRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.type.GuideAvailableType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("guide") @Primary
@Slf4j
public class GuideServiceImpl implements GuideService {
    private final EventRepository eventRepository;
    private final GuidRepository guidRepository;
    private final CrewRepository crewRepository;
    private final MemberRepository memberRepository;
    @Override
    public void processPayment(Long memberId, Long eventId, List<Kid> kids) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));
        Member company = null;
        if(memberId != null){
            company = memberRepository.findById(memberId).get();
        }


//        가장 최근 하나 가져오기
        Guide guide = guidRepository.findFirstByEventIdAndAvailableTypeOrderById(eventId, GuideAvailableType.AVAILABLE);

//         현재 들어온 아이들수와 남아있는 가이드의 아이들 수를 더한게 10보다 작다면
        if(guide.getCrews().stream().count() + kids.stream().count() >= 10){
//            가이드의 status를 불가능으로 만들고
            guide.setAvailableType(GuideAvailableType.UNAVAILABLE);
//            새로운 가이드 가져오기
            Guide newGuide = guidRepository.findFirstByEventIdAndAvailableTypeOrderById(eventId, GuideAvailableType.AVAILABLE);
                if(newGuide == null){
                    Guide guide1 = new Guide(event, company);
                   Guide savedGuide  = guidRepository.save(guide1);
                    kids.stream().forEach(kid -> {
                        Crew crew = new Crew(kid, savedGuide);
                        crewRepository.save(crew);
                    });
                } else{
                    kids.stream().forEach(kid -> {
                        Crew crew = new Crew(kid, newGuide);
                        crewRepository.save(crew);
                    });
                }
//            만약에 10보다 작으면 바로 처음 가져온 가이드에 저장
        } else if(guide.getCrews().stream().count() + kids.stream().count() < 10){
            kids.stream().forEach(kid -> {
                Crew crew = new Crew(kid, guide);
                crewRepository.save(crew);
            });
        }

    }

}
