package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void saveTest(){
        Review review = new Review(5, eventRepository.findEventById_QueryDSL(2L).get(), memberRepository.findById(1L).get(), null, "asd", "asd");
        reviewRepository.save(review);
    }


//    memberId 로 내가쓴 후기 조회
    @Test
    public void findReviewByIdTest(){
        reviewRepository.findReviewById_QueryDSL(1L).stream().map(Review::toString).forEach(log::info);
    }




}
