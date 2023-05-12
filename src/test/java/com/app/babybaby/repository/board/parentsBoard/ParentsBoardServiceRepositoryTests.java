package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.file.parentsBoardFile.ParentsBoardFileRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.reply.parentsBoardReply.ParentsBoardReplyRepository;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.type.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ParentsBoardServiceRepositoryTests {
    @Autowired
    MemberRepository memberRepository;


    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParentsBoardRepository parentsBoardRepository;

    @Autowired
    ParentsBoardReplyRepository parentsBoardReplyRepository;

    @Autowired
    ParentsBoardFileRepository parentsBoardFileRepository;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;


    //    회원 20명 저장
    @Test
    @Transactional
    public void save20MemberTest() {
        Address address = new Address();
        address.setAddress("분당");
        address.setAddressDetail("d");
        address.setAddressSubDetail("dfa");
        address.setPostcode("12342132");

        for (int i = 0; i < 20; i++) {
            String uniqueNickname = "Nickname" + i;

            Member member = new Member(
                    "you" + i + "@gmail.com",
                    "이름" + i,
                    "1234",
                    uniqueNickname,
                    "안녕하세요",
                    "0101234123" + i,
                    address,
                    "defaultImage.png",
                    "defaultImageUUID",
                    "/defaultImage",
                    LocalDateTime.now(),
                    MemberType.COMPANY,
                    AcceptanceType.ACCEPTED,
                    SleepType.AWAKE,
                    GuideType.NON_DISABLED,
                    CategoryType.AGRICULTURE,
                    "",
                    "",
                    ""
            );

            memberRepository.save(member);
        }
    }


    //  게시글 20개 저장
    @Transactional
    @Test
    public void save20EventTest() {
        for (int i = 0; i < 20; i++) {
            Address address = new Address();
            address.setAddress("");
            address.setAddressDetail("");
            address.setAddressSubDetail("");
            address.setPostcode("");

            Event event = new Event(
                    "",
                    "",
                    (long) (i + 1),
                    address,
                    40000L,
                    CategoryType.AGRICULTURE,
                    null,
                    null
            );

            eventRepository.save(event);
        }
    }


    //    부모님마당 20개 저장
    @Transactional
    @Test
    public void save20ParentsBoardTest() {
        List<Event> events = eventRepository.findAll();
        List<Member> members = memberRepository.findAll();

        for (int i = 0; i < 20; i++) {
            Event event = events.get(i % events.size());
            Member member = members.get(i % members.size());
            List<ParentsBoardReply> parentsBoardReplies = new ArrayList<>();

            ParentsBoard parentsBoard = new ParentsBoard(
                    "",
                    "",
                    event,
                    member,
                    parentsBoardReplies
            );

            parentsBoardRepository.save(parentsBoard);
        }
    }


    //    부모님 마당 댓글 20개 저장
    @Transactional
    @Test
    public void save20ParentsBoardReplyTest() {
        List<ParentsBoard> parentsBoards = parentsBoardRepository.findAll();
        List<Member> members = memberRepository.findAll();

        for (int i = 0; i < 20; i++) {
            ParentsBoard parentsBoard = parentsBoards.get(i % parentsBoards.size());
            Member member = members.get(i % members.size());

            ParentsBoardReply parentsBoardReply = new ParentsBoardReply(
                    "",
                    parentsBoard,
                    member
            );
            parentsBoardReplyRepository.save(parentsBoardReply);
        }
    }

    //    댓글삭제
    @Transactional
    @Test
    public void deleteParentsBoardReplyTest() {
        List<ParentsBoardReply> parentsBoardreplys = parentsBoardReplyRepository.findAll();

        for (int i = 0; i < 20; i++) {
            ParentsBoardReply parentsBoardReply = parentsBoardreplys.get(i % parentsBoardreplys.size());
            parentsBoardReplyRepository.delete(parentsBoardReply);
        }

    }

//    @Test
//    public void findFreeBoardListByCategoryTypeAndMemberIdTest(){
//        Pageable pageable = PageRequest.of(0, 10);
//        freeBoardRepository.findFreeBoardListByCategoryTypeAndMemberId(pageable,CategoryType.CULTURE, 5L).stream().map(FreeBoard::toString).forEach(log::info);
//    }

    @Test
    @Transactional
    public void parentsBoardListTest() {
        ParentsBoardSearch parentsBoardSearch = new ParentsBoardSearch();
        Pageable pageable = PageRequest.of(1, 10);
        parentsBoardRepository.findAllWithSearch(pageable, parentsBoardSearch)
                .get()
                .map(ParentsBoard::toString)
                .forEach(log::info);
    }

    @Test
    @Transactional
    public void findByEventIdTest() {
        log.info(parentsBoardRepository.findByEventId(1L).toString());
    }

    @Test
    @Transactional
    public void findDetailByIdTest() {
        log.info(parentsBoardRepository.findDetailById(521L).toString());
    }


}