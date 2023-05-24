package com.app.babybaby.repository.member.crew;


import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.member.kid.KidRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.type.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CrewRepositoryTests {
    @Autowired
    CrewRepository crewRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    KidRepository kidRepository;

//    @Test
//    public void saveTest(){
//        Address address = new Address();
//        address.setAddress("서울시");
//        address.setAddressDetail("노원구");
//        address.setAddressSubDetail("월계동");
//        address.setPostcode("1111");
//
//        LocalDateTime registerDate = LocalDateTime.now();
//        Member member = new Member("member2@example.com", "홍길동", "password", "nickname2", "hi sentence2", "011-1234-5178", address, "profile_original_name.png", "profile_uuid", "/profile/path", registerDate, MemberType.COMPANY, AcceptanceType.ACCEPTED, SleepType.AWAKE, GuideType.DISABLED, CategoryType.SPORTS, "file_path", "file_uuid", "file_original_name");
//        memberRepository.save(member);
//
//       Kid kid = new Kid("김동한", 13, GenderType.MAN, member);
//        kidRepository.save(kid);
//
//    }
    @Test
    public void saveTest() {
        Address address = new Address();
        address.setAddress("서울시");
        address.setAddressDetail("노원구");
        address.setAddressSubDetail("월계동");
        address.setPostcode("1111");

        LocalDateTime registerDate = LocalDateTime.now();

        Member member = new Member(
                "member2@example.com",
                "홍길동",
                "password",
                "nickname2",
                "hi sentence2",
                "011-1234-5178",
                address,
                "profile_original_name.png",
                "profile_uuid",
                "/profile/path", registerDate,
                MemberType.COMPANY,
                AcceptanceType.ACCEPTED,
                SleepType.AWAKE,
                GuideType.DISABLED,
                CategoryType.SPORTS,
                "file_path",
                "file_uuid",
                "file_original_name");

        memberRepository.save(member);

//        Kid kid = new Kid("김동한", 13, GenderType.MAN, member);
//        kidRepository.save(kid);
    }


    @Test
    public void findById(){
        log.info(crewRepository.findById(1L).toString());
    }

    @Test
//    이거는 memberID 로 아이 전체 조회
    public void findKidByUserIdTest(){
        crewRepository.findKidByUserId(1L).stream().map(Kid::toString).forEach(log::info);
    }

    @Test
    public void TestFind(){
        LocalDateTime date = LocalDateTime.of(2023, Month.MAY, 24, 15, 45, 30);
        log.info(crewRepository.findCrewByMemberId(1L, date).toString());
    }

}
