package com.app.babybaby.service.board.review;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.fileDTO.reviewFileDTO.ReviewFileDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.file.reviewFile.ReviewFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.entity.reply.reviewReply.ReviewReply;
import com.app.babybaby.exception.BoardNotFoundException;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.review.ReviewRepository;
import com.app.babybaby.repository.file.reviewFile.ReviewFileRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.purchase.coupon.CouponRepository;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.service.board.event.EventService;
import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.CouponStatus;
import com.app.babybaby.type.CouponType;
import com.app.babybaby.type.FileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final EventService eventService;

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final EventRepository eventRepository;

    private final ReviewFileRepository reviewFileRepository;

    private final CouponRepository couponRepository;

    @Override
    public Page<ReviewDTO> findReviewById(Long memberId, Pageable pageable) {

        Page<Review> reviews = reviewRepository.findReviewById_QueryDSL(pageable,memberId);
        List<ReviewDTO> reviewDTOS = reviews.stream().map(review -> ReviewToDTO(review)).collect(Collectors.toList());
        log.info(reviewDTOS.toString()+"서비스");
        log.info(reviews.toString()+"리뷰스");
        return new PageImpl<>(reviewDTOS,pageable,reviews.getTotalElements());
    }
//    [리뷰] 회원 아이디로 모든 결제한 이벤트 가져오기
    @Override
    public List<EventDTO> findAllEventsByMemberId(Long memberId) {
        List<Event> events = eventRepository.findAllPurchasedEvents(memberId);
        List<EventDTO> eventDTOS = events.stream().map(eventService::eventToDTO).collect(Collectors.toList());
        return eventDTOS;
    }

    @Override
    public void saveReview(Long memberId, Long eventId, ReviewDTO reviewDTO) {
        Member member = memberRepository.findById(memberId).get();
        Event event = eventRepository.findById(eventId).get();
        List<ReviewFile> reviewFiles = reviewDTO.getFiles().stream().map(this::toReivewFileEntity).collect(Collectors.toList());
        Coupon coupon = new Coupon(CouponType.REVIEW, CouponStatus.UNUSED, 5000L, member);
        Review review = new Review(reviewDTO.getBoardTitle(), reviewDTO.getBoardContent(), reviewDTO.getReviewScore(), reviewFiles, event, member);
        Review savedReview = reviewRepository.save(review);
        couponRepository.save(coupon);
        
        log.info("방금 세이브한 리뷰 : " + savedReview.toString());
        reviewFiles.forEach(reviewFile -> {
//            String fileOriginalName, String fileUUID, String filePath, Review review, FileType fileStatus
            ReviewFile reviewFile1 = new ReviewFile(reviewFile.getFileOriginalName(), reviewFile.getFileUUID(), reviewFile.getFilePath(), savedReview, reviewFile.getFileStatus());
            reviewFileRepository.save(reviewFile1);
        });
    }

    @Override
    public Member getMemberInfo(Long sessionId) {
        Member member = memberRepository.findById(sessionId).get();
        return member;
    }


    public Page<ReviewDTO> getFindAllWithSearchReviewBoardList(Pageable pageable, ParentsBoardSearch parentsBoardSearch) {
        Page<Review> reviews = reviewRepository.findAllReviewWithSearch_QueryDsl(pageable, parentsBoardSearch);
        List<ReviewDTO> reviewDTOS = reviews.stream().map(this::ReviewToDTO).collect(Collectors.toList());
        reviewDTOS.forEach(reviewDTO -> {
        });
        return new PageImpl<>(reviewDTOS, pageable, reviews.getTotalElements());
    }

    @Override
    public List<ReviewDTO> find2RecentDesc(CategoryType categoryType) {
        List<Review> reviews = reviewRepository.find2RecentDesc(categoryType);
        List<ReviewDTO> reviewDTOList = reviews.stream().map(review -> ReviewToDTO(review)).collect(Collectors.toList());
        return reviewDTOList;
    }

    @Override
    public ReviewDTO getReviewBoardDetail(Long id) {
        com.app.babybaby.entity.board.review.Review review = reviewRepository.findDetailById_QueryDsl(id).orElseThrow(() -> {
            throw new BoardNotFoundException();
        });
        return ReviewToDTO(review);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).get();
    }


}
