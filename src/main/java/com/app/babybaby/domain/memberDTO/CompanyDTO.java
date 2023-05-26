package com.app.babybaby.domain.memberDTO;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.type.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class CompanyDTO {
    private Long memberId;
    private String  memberEmail;
    private String memberName;
    private String memberPassword;
    private String memberNickname;
    private String memberHiSentence;
    private String memberPhone;
    private Address memberAddress;
    private String memberProfileOriginalName;
    private String memberProfileUUID;
    private String memberProfilePath;
    private MemberType memberType;
    private Role memberRole;
    private AcceptanceType memberGuideStatus;
    private SleepType memberSleep;
    private GuideType memberGuideType;
    private CategoryType eventCategory;
    private Long totalEventsCount;
    private Long totalReviewCount;
    private Boolean hasNextPage;
    private List<EventDTO> events;
    private AlertReadStatus alertReadStatus;

    private List<EventDTO> finishedEvents;
    private List<EventDTO> upcommingEvents;
    private List<EventDTO> nowEvents;
    private List<ReviewDTO> reviews;

    @Builder
    public CompanyDTO(AlertReadStatus alertReadStatus, Long memberId, String memberEmail, String memberName, String memberPassword, String memberNickname, String memberHiSentence, String memberPhone, Address memberAddress, String memberProfileOriginalName, String memberProfileUUID, String memberProfilePath, MemberType memberType, Role memberRole, AcceptanceType memberGuideStatus, SleepType memberSleep, GuideType memberGuideType, CategoryType eventCategory, Long totalEventsCount, List<EventDTO> events, List<EventDTO> finishedEvents, List<EventDTO> upcommingEvents, List<EventDTO> nowEvents, List<ReviewDTO> reviews) {
        this.alertReadStatus = alertReadStatus;
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberHiSentence = memberHiSentence;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.memberProfileOriginalName = memberProfileOriginalName;
        this.memberProfileUUID = memberProfileUUID;
        this.memberProfilePath = memberProfilePath;
        this.memberType = memberType;
        this.memberRole = memberRole;
        this.memberGuideStatus = memberGuideStatus;
        this.memberSleep = memberSleep;
        this.memberGuideType = memberGuideType;
        this.eventCategory = eventCategory;
        this.totalEventsCount = totalEventsCount;
        this.events = events;
        this.finishedEvents = finishedEvents;
        this.upcommingEvents = upcommingEvents;
        this.nowEvents = nowEvents;
        this.reviews = reviews;
    }
}
