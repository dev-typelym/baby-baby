package com.app.babybaby.service.admin.adminReviewBoard;

import com.app.babybaby.domain.adminDTO.AdminReviewDTO;
import com.app.babybaby.domain.fileDTO.reviewFileDTO.ReviewFileDTO;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.file.reviewFile.ReviewFile;
import com.app.babybaby.search.admin.AdminReviewSearch;
import com.app.babybaby.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface AdminReviewBoardSerivce {
    //    관리자 리뷰 목록
    public Page<AdminReviewDTO> getAdminReviewListWithPaging(int page, AdminReviewSearch adminReviewSearch, CategoryType eventCategory);

    //    관리자 리뷰 상세 보기
    public AdminReviewDTO getAdminReviewById(Long boardId);

    //    관리자 리뷰 삭제하기
    public void deleteAdminReview(List<String> boardIds);

    default AdminReviewDTO toAdminReviewDTO(Review review){
        return AdminReviewDTO.builder()
                .id(review.getId())
                .eventCategory(review.getEvent().getCategory())
                .eventRecruitCount(review.getEvent().getEventRecruitCount())
                .eventTitle(review.getEvent().getBoardTitle())
                .startDate(review.getEvent().getCalendar().getStartDate())
                .nickName(review.getMember().getMemberNickname())
                .reviewScore(review.getReviewScore())
                .writeDate(review.getRegisterDate())
                .reviewTitle(review.getBoardTitle())
                .reviewContent(review.getBoardContent())
                .reviewFileDTOS(review.getReviewFiles().stream().map(reviewFile -> reviewFileToDTO(reviewFile)).collect(Collectors.toList()))
                .build();

    }

    default ReviewFileDTO reviewFileToDTO(ReviewFile reviewFile){
        return ReviewFileDTO.builder()
                .fileOriginalName(reviewFile.getFileOriginalName())
                .filePath(reviewFile.getFilePath())
                .fileUUID(reviewFile.getFileUUID())
                .fileStatus(reviewFile.getFileStatus())
                .build();
    }
}
