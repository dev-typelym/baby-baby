package com.app.babybaby.repository.alert.alertCrew;

import com.app.babybaby.entity.alert.alertCrew.AlertCrew;
import com.app.babybaby.entity.member.Crew;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.member.crew.CrewRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.type.AlertType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AlertCrewRepositoryTests {
    @Autowired
    private AlertCrewRepository alertCrewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CrewRepository crewRepository;

    @Test
    public void saveTest(){
        Optional<Member> member = memberRepository.findById(1L);
        Optional<Crew> crew = crewRepository.findById(9999L);

        member.ifPresent(member1 -> {
            crew.ifPresent(crew1 -> {
//                AlertCrew alertCrew = new AlertCrew("앙앙", "앙앙앙", AlertType.EVENT, member1, LocalDateTime.now(), crew1);
//                alertCrewRepository.save(alertCrew);
            });
        });
    }
}
