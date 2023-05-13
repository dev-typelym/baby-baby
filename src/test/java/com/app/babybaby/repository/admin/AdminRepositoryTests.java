package com.app.babybaby.repository.admin;


import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.reply.parentsBoardReply.QParentsBoardReply;
import com.app.babybaby.entity.reply.reviewReply.QReviewReply;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.repository.board.review.ReviewRepository;
import com.app.babybaby.repository.calendar.CalendarRepository;
import com.app.babybaby.repository.file.nowKidsFile.NowKidsFileRepository;
import com.app.babybaby.repository.guideSchedule.GuideScheduleRepository;
import com.app.babybaby.repository.member.crew.CrewRepository;
import com.app.babybaby.repository.member.guide.GuidRepository;
import com.app.babybaby.repository.member.kid.KidRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.reply.parentsBoardReply.ParentsBoardReplyRepository;
import com.app.babybaby.repository.reply.reviewReply.ReviewReplyRepository;
import com.app.babybaby.search.admin.AdminMemberSearch;
import com.app.babybaby.type.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.app.babybaby.type.CategoryType.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AdminRepositoryTests {

    @Autowired
    NowKidsRepository nowKidsRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParentsBoardRepository parentsBoardRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewReplyRepository reviewReplyRepository;

    @Autowired
    ParentsBoardReplyRepository parentsBoardReplyRepository;

    @Autowired
    NowKidsFileRepository nowKidsFileRepository;

    @Autowired
    KidRepository kidRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    GuidRepository guidRepository;

    @Autowired
    GuideScheduleRepository guideScheduleRepository;

    @Autowired
    CrewRepository crewRepository;


//    부모님마당 입력하기
    @Test
    public void parentsBoardSaveTest() {
        LocalDateTime registerDate = LocalDateTime.now();
        Address address = new Address();
        address.setAddress("성남시");
        address.setAddressDetail("수정구");
        address.setAddressSubDetail("태평동");
        address.setPostcode("112232");

//        Member member = new Member("lym3031@gmail.com", "임의택", "1212", "nickname1", "hi sentence22", "010-4444-5555", address, "profile_original_name.png", "profile_uuid", "/profile/path", registerDate, MemberType.GENERAL,  SleepType.AWAKE, "file_path", "file_uuid", "file_original_name");

//        memberRepository.save(member);
//
//        Calendar calendar = new Calendar("예술날", ART, LocalDateTime.now(), LocalDateTime.now());
//
//        Event event  = new Event(25L, address, 150000L, ART, calendar, member);
//
//        eventRepository.save(event);
//        List<ParentsBoardReply> parentsBoardReplies = new ArrayList<>();
//
//        ParentsBoard parentsBoard = new ParentsBoard("부모님마당제목1", "부모님마당내용1", event, member, parentsBoardReplies);
//        parentsBoardRepository.save(parentsBoard);
//        ParentsBoardReply parentsBoardReply = new ParentsBoardReply("싫어요", parentsBoard ,member);
    }

//    NowKids 게시판 입력
@Test
public void save50NowKids() {
    Address address = new Address();
    address.setAddress("분당");
    address.setAddressDetail("d");
    address.setAddressSubDetail("dfa");
    address.setPostcode("12342132");
    for (int i = 11; i < 20; i++) {
        String uniqueNickname = "Bool" + i;

        Member member = new Member("you" + i + "@naver.com", "정유찬", "1234", uniqueNickname, "안녕하세요",
                "0101234123" + i, address, null, null,null,LocalDateTime.now(), MemberType.COMPANY, AcceptanceType.ACCEPTED, SleepType.AWAKE, GuideType.NON_DISABLED, CategoryType.AGRICULTURE,null,null,null);
        Calendar calendar = new Calendar("이벤트1", CategoryType.AGRICULTURE, LocalDateTime.now(), LocalDateTime.now());
//            public Event (String boardTitle, String boardContent, Long eventRecruitCount, Address eventLocation, Long eventPrice, String eventContent, CategoryType category, Calendar calendar) {
        Event event = new Event( "Test" + (i + 1), "test" +(i+1), 10L, address, 10000L, SPORTS, calendar, member);
        memberRepository.save(member);
        eventRepository.save(event);
        NowKids nowKids = new NowKids(event, member);
        nowKidsRepository.save(nowKids);
    }
}



/// 관리자 회원 목록 조회
    @Test
    public void findAllMemberWithSearchTest(){
        //        ProductSearch productSearch = new ProductSearch();
//        productSearch.setProductPrice(4000L);
//        productSearch.setProductStock(702L);
//        memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));

        // 회원 목록 조회

        AdminMemberSearch adminMemberSearch = new AdminMemberSearch();
        adminMemberSearch.setMemberName(null);
        Page<Member> result = memberRepository.findAllMemberWithSearch_queryDSL(PageRequest.of(1, 5), adminMemberSearch);

        // 회원 목록 출력
        List<Member> members = result.getContent();
        log.info("Total Members: {}", result.getTotalElements());
        for (Member member : members) {
            log.info("Member: {}", member);
        }
    }
//  관리자 회원 상세보기
    @Test
    public void findMemberInfoByIdTest(){
        memberRepository.findMemberInfoById_QueryDsl(1L).ifPresent(member -> log.info(member.toString()));
    }

//  관리자 회원 삭제
    @Test
    public void disableMembersByIdsTest(){
        memberRepository.disableMembersByIds_queryDSL(Arrays.asList(1L));
    }

//  관리자 기업 목록 조회
    @Test
    public void findAllCompanyWithSearchTest(){
//        ProductSearch productSearch = new ProductSearch();
//        productSearch.setProductPrice(4000L);
//        productSearch.setProductStock(702L);
//        memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));

        // 기업 목록 조회
//        Page<Member> companyResult = memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));

        // 기업 목록 출력
//        List<Member> companys = companyResult.getContent();
//        log.info("Total companys: {}", companyResult.getTotalElements());
//        for (Member company : companys) {
//            log.info("company: {}", company);
//        }
    }
    //  관리자 기업 행사 수
    @Test
    public void findCompanyOpenEventsCount_QueryDsl(){
        Optional<Long>count = memberRepository.findCompanyOpenEventsCount_QueryDsl(4L);
        log.info(String.valueOf(count));
    }

    //  관리자 기업 상세
    @Test
    public void findEventInfoBycompanyIdTest(){
        List<Event> events = memberRepository.findEventInfoBycompanyId_QueryDsl(4L);
        for (Event event : events) {
            log.info(event.toString());
        }
    }

    //  관리자 지금우리아이들은 목록 조회
    @Test
    public void findNowKidsWithSearchTest(){
        //        ProductSearch productSearch = new ProductSearch();
//        productSearch.setProductPrice(4000L);
//        productSearch.setProductStock(702L);
//        memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));

        // 지금우리아이들은 목록 조회
        List<NowKids> events = nowKidsRepository.findNowKidsEvents_queryDSL(null, SPORTS,"종료");


        // 지금 우리아이들은 목록 출력
        log.info("Total companys: {}", events.size());
        for (NowKids event : events) {
            log.info("Event: {}", event);
        }
    }

    //  관리자 이벤트 목록 조회
    @Test
    public void findEventWithSearchTest(){
        //        ProductSearch productSearch = new ProductSearch();
//        productSearch.setProductPrice(4000L);
//        productSearch.setProductStock(702L);
//        memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));

        // 이벤트 목록 조회
        List<Event> events = eventRepository.findNowKidsEvents_queryDSL( null ,MUSEUM   ,"전체");


        // 지금 이벤트 목록 출력
        log.info("Total companys: {}", events.size());
        /*for (Event event : events) {
            log.info("Event: {}", event);
        }*/
    }

    /// 관리자 부모님마당 목록 조회
    @Test
    public void findAllParentsBoardWithSearchTest(){
        //        ProductSearch productSearch = new ProductSearch();
//        productSearch.setProductPrice(4000L);
//        productSearch.setProductStock(702L);
//        memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));

        // 회원 목록 조회
//        Page<ParentsBoard> result = parentsBoardRepository.findAllParentsBoardWithSearch_queryDSL(PageRequest.of(1, 5));

        // 회원 목록 출력
//        List<ParentsBoard> boards = result.getContent();
//        log.info("Total boards: {}", result.getTotalElements());
//        for (ParentsBoard board : boards) {
//            log.info("board: {}", board);
//        }
    }

//  관리자 부모님마당 상세
    @Test
    public void findParentsBoardByIdTest(){
        parentsBoardRepository.findParentsBoardById_queryDSL(1L).ifPresent(member -> log.info(member.toString()));
    }


    /// 관리자 리뷰 목록 조회
    @Test
    public void findAllReviewBoardWithSearchTest(){
        //        ProductSearch productSearch = new ProductSearch();
//        productSearch.setProductPrice(4000L);
//        productSearch.setProductStock(702L);
//        memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));

        // 회원 목록 조회
//        Page<Review> result = reviewRepository.findAllReviewBoardWithSearch_queryDSL(PageRequest.of(1, 5), null);

        // 회원 목록 출력
//        List<Review> reviews = result.getContent();
//        log.info("Total reviews: {}", result.getTotalElements());
//        for (Review review : reviews) {
//            log.info("Review: {}", review);
//        }
    }

//  관리자 리뷰 상세
    @Test
    public void findReviewBoardByIdTest(){
        reviewRepository.findReviewBoardById_queryDSL(1L).ifPresent(review -> log.info(review.toString()));
    }


    /// 부모님마당 댓글 목록 조회
    @Test
    public void findAllParentsBoardReplyWithSearchTest(){
        //        ProductSearch productSearch = new ProductSearch();
//        productSearch.setProductPrice(4000L);
//        productSearch.setProductStock(702L);
//        memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));
        QParentsBoardReply parentsBoardReply = QParentsBoardReply.parentsBoardReply;
        // 회원 목록 조회
//        Page<ParentsBoardReply> result = parentsBoardReplyRepository.findAlLParentsBoardReplyWithSearch_queryDSL(PageRequest.of(1, 5));
        // 회원 목록 출력
//        List<ParentsBoardReply> replies = result.getContent();
//        for (ParentsBoardReply reply : replies) {
//            log.info("Reply: {}", reply);
//        }
    }

    /// 리뷰 댓글 목록 조회
    @Test
    public void findAllReviewReplyWithSearchTest(){
        //        ProductSearch productSearch = new ProductSearch();
//        productSearch.setProductPrice(4000L);
//        productSearch.setProductStock(702L);
//        memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));
        QReviewReply reviewReply = QReviewReply.reviewReply;
        // 회원 목록 조회
//        Page<ReviewReply> result = reviewReplyRepository.findAlLReviewReplyWithSearch_queryDSL(PageRequest.of(1, 5));
        // 회원 목록 출력
//        List<ReviewReply> replies = result.getContent();
//        for (ReviewReply reply : replies) {
//            log.info("Reply: {}", reply);
//        }
    }

    /// 통솔자 관리 목록 조회
    @Test
    public void findAllGuideWithSearchTest(){
        //        ProductSearch productSearch = new ProductSearch();
//        productSearch.setProductPrice(4000L);
//        productSearch.setProductStock(702L);
//        memberRepository.findAllCompanyWithSearch_queryDSL(PageRequest.of(1, 10));
        // 회원 목록 조회
//        Page<Member> result = memberRepository.findAllGuideWithSearch_queryDSL(PageRequest.of(1, 5), GuideType.DISABLED, AcceptanceType.ACCEPTED);
        // 회원 목록 출력
//        List<Member> guides = result.getContent();
//        log.info("Total guides: {}", result.getTotalElements());
//        for (Member guide : guides) {
//            log.info("Guide: {}", guide);
//        }
    }

    //// 지금우리아이들은 게시글 삭제
    @Test
    public void deleteNowKidsTest(){
        nowKidsRepository.deleteNowKidsByIds_queryDSL(Arrays.asList(26L, 30L));
    }

}
