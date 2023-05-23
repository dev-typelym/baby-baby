package com.app.babybaby.domain.boardDTO.eventDTO;

import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.domain.purchaseDTO.couponDTO.CouponDTO;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.purchase.coupon.Coupon;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.FileType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
@RequiredArgsConstructor
public class EventDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private Long eventRecruitCount;
    private Address eventLocation;
    private Long eventPrice;
    private CategoryType category;
    private EventBoardSearch eventBoardSearch;
    private CalendarDTO calendar;
//    private MemberDTO company;
    private Boolean isEventLiked;
    private Long memberId;
    private String memberName;
    private String memberNickname;
    private String memberEmail;
    private String memberHiSentence;
    private String memberPhone;
    private Address memberLocation;
    private String mainFileOriginalName;
    private String mainFileUUID;
    private String mainFilePath;
    private FileType fileType;
    private List<EventFileDTO> files;
    private List<CouponDTO> coupons;

    private Long totalCouponCount;
    private Long totalUnusedCouponCount;

    private String memberProfilePath;
    private String memberProfileUUID;
    private String memberProfileOriginalName;



    @Builder
    public EventDTO(Long id, String boardTitle, String boardContent, Long eventRecruitCount, Address eventLocation, Long eventPrice, CategoryType category, EventBoardSearch eventBoardSearch, CalendarDTO calendar, Boolean isEventLiked, Long memberId, String memberName, String memberNickname, String memberEmail, String memberHiSentence, String memberPhone, Address memberLocation, String mainFileOriginalName, String mainFileUUID, String mainFilePath, FileType fileType, List<EventFileDTO> files, String memberProfilePath, String memberProfileUUID, String memberProfileOriginalName) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.eventRecruitCount = eventRecruitCount;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.category = category;
        this.eventBoardSearch = eventBoardSearch;
        this.calendar = calendar;
        this.isEventLiked = isEventLiked;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.memberHiSentence = memberHiSentence;
        this.memberPhone = memberPhone;
        this.memberLocation = memberLocation;
        this.mainFileOriginalName = mainFileOriginalName;
        this.mainFileUUID = mainFileUUID;
        this.mainFilePath = mainFilePath;
        this.fileType = fileType;
        this.files = files;
        this.memberProfilePath = memberProfilePath;
        this.memberProfileUUID = memberProfileUUID;
        this.memberProfileOriginalName = memberProfileOriginalName;
    }
    //    private List<EventFileDTO> eventFileDTOS;

//    private CalendarDTO calendarDTO;
}
