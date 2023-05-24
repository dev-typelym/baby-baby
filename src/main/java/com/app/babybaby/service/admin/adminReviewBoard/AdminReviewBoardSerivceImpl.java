package com.app.babybaby.service.admin.adminReviewBoard;

import com.app.babybaby.domain.adminDTO.AdminReviewDTO;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.repository.board.review.ReviewRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.search.admin.AdminReviewSearch;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminReviewBoardSerivceImpl implements AdminReviewBoardSerivce {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParentsBoardRepository parentsBoardRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    //    관리자 리뷰 목록
    @Override
    public Page<AdminReviewDTO> getAdminReviewListWithPaging(int page, AdminReviewSearch adminReviewSearch, CategoryType eventCategory) {
        Page<Review> reviews = reviewRepository.findAllReviewBoardWithSearch_queryDSL(PageRequest.of(page, 5), adminReviewSearch, eventCategory);
        List<AdminReviewDTO> adminReviewDTOS = reviews.getContent().stream()
                .map(this::toAdminReviewDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(adminReviewDTOS, reviews.getPageable(), reviews.getTotalElements());
    }

    //    관리자 리뷰 상세
    @Override
    public AdminReviewDTO getAdminReviewById(Long boardId) {
        Optional<Review> review = reviewRepository.findReviewBoardById_queryDSL(boardId);
        AdminReviewDTO adminReviewDTO = toAdminReviewDTO(review.get());
        return adminReviewDTO;
    }

    //    관리자 리뷰 삭제
    @Override
    public void deleteAdminReview(List<String> boardIds) {
        boardIds.stream().map(boardId -> Long.parseLong(boardId)).forEach(reviewRepository::deleteReviewBoardByIds_queryDSL);
    }
}
