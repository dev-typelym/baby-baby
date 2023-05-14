package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.audit.Period;
import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ReviewRepositoryTests {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ParentsBoardRepository parentsBoardRepository;


    @Test
    public void saveTest(){
        Review review = new Review(5, eventRepository.findEventById_QueryDSL(2L).get(), memberRepository.findById(1L).get(), null, "asd", "asd");
        reviewRepository.save(review);
    }

//    부모님마당 넣기
    @Test
    public void saveTests(){
        ParentsBoard parentsBoard = new ParentsBoard(
                "Test",
                "test",
                eventRepository.findById(2L).get(),
                memberRepository.findById(1L).get(),
                null
        );

        parentsBoardRepository.save(parentsBoard);

    }


//    memberId 로 내가쓴 후기 조회
    @Test
    public void findReviewByIdTest(){
        Pageable pageable = PageRequest.of(0, 10);
        reviewRepository.findReviewById_QueryDSL(pageable,1L).stream().forEach(v -> log.info(v.toString()));
    }




}
