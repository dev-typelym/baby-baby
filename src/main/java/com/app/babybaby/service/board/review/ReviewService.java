package com.app.babybaby.service.board.review;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.fileDTO.reviewFileDTO.ReviewFileDTO;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.file.reviewFile.ReviewFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public interface ReviewService {
    public Page<ReviewDTO> findReviewById(Long memberId, Pageable pageable);

    //    [리뷰] 회원 아이디로 모든 결제한 이벤트 가져오기
    public List<EventDTO> findAllEventsByMemberId(Long memberId);

    public void saveReview(Long memberId, Long eventId, ReviewDTO reviewDTO);

    public Member getMemberInfo(Long sessionId);

//    리스트 ajax
    public Page<ReviewDTO> getFindAllWithSearchParentsBoardList(Pageable pageable, ParentsBoardSearch parentsBoardSearch);

    default ReviewDTO ReviewToDTO(Review review){
        return ReviewDTO.builder()
                .boardTitle(review.getBoardTitle())
                .boardContent(review.getBoardContent())
                .id(review.getId())
                .memberId(review.getMember().getId())
                .reviewScore(review.getReviewScore())
                .memberNickName(review.getMember().getMemberNickname())
                .memberProfileOriginalName(review.getMember().getMemberProfileOriginalName())
                .memberProfilePath(review.getMember().getMemberProfilePath())
                .updateDate(review.getUpdateDate())
                .uploadDate(review.getRegisterDate())
                .eventCategory(review.getEvent().getCategory())
                .eventContent(review.getBoardContent())
                .eventId(review.getEvent().getId())
                .eventLocation(review.getEvent().getEventLocation())
                .eventPrice(review.getEvent().getEventPrice())
                .eventRecruitCount(review.getEvent().getEventRecruitCount())
                .eventTitle(review.getEvent().getBoardTitle())
                .files(review.getReviewFiles().stream().map(this::toReviewFileDTO).collect(Collectors.toList()))
                .build();
    }

    default ReviewFileDTO toReviewFileDTO(ReviewFile reviewFile){
        return ReviewFileDTO.builder()
                .fileOriginalName(reviewFile.getFileOriginalName())
                .filePath(reviewFile.getFilePath())
                .fileUUID(reviewFile.getFileUUID())
                .fileStatus(reviewFile.getFileStatus())
                .build();
    }

    default Review toReviewEntity(ReviewDTO reviewDTO){
        return Review.builder()
                .boardTitle(reviewDTO.getBoardTitle())
                .boardContent(reviewDTO.getBoardContent())
                .reviewFiles(reviewDTO.getFiles().stream().map(this::toReivewFileEntity).collect(Collectors.toList()))
                .build();

    }

    default  ReviewFile toReivewFileEntity(ReviewFileDTO reviewFileDTO){
        return ReviewFile.builder()
                .fileOriginalName(reviewFileDTO.getFileOriginalName())
                .filePath(reviewFileDTO.getFilePath())
                .fileStatus(reviewFileDTO.getFileStatus())
                .fileUUID(reviewFileDTO.getFileUUID())
                .build();

    }

}
