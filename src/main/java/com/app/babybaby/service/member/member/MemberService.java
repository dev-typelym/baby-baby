package com.app.babybaby.service.member.member;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.memberDTO.CompanyDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.service.board.event.EventService;
import com.app.babybaby.service.board.event.EventServiceImpl;
import com.app.babybaby.service.board.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface MemberService {

    public Optional<Member> getMemberById(Long memberId);

    public CompanyDTO getAllMemberInfo(Long companyId);

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .memberNickname(member.getMemberNickname())
                .id(member.getId())
                .memberProfileUUID(member.getMemberProfileUUID())
                .memberProfilePath(member.getMemberProfilePath())
                .memberProfileOriginalName(member.getMemberProfileOriginalName())
                .build();
    }

    default EventDTO eventToDTO(Event event){
        return EventDTO.builder()
                .id(event.getId())
                .boardContent(event.getBoardContent())
                .boardTitle(event.getBoardTitle())
                .category(event.getCategory())
                .eventLocation(event.getEventLocation())
                .eventPrice(event.getEventPrice())
                .eventRecruitCount(event.getEventRecruitCount())
                .build();
    }

    default ReviewDTO ReviewToDTO(Review review){
        return ReviewDTO.builder()
                .boardTitle(review.getBoardTitle())
                .id(review.getId())
                .memberId(review.getMember().getId())
                .reviewScore(review.getReviewScore())
                .build();
    }

    default CompanyDTO toCompanyDTO(Member member){
        return CompanyDTO.builder()
                .memberNickname(member.getMemberNickname())
                .memberAddress(member.getMemberAddress())
                .memberEmail(member.getMemberEmail())
                .memberGuideStatus(member.getMemberGuideStatus())
                .memberHiSentence(member.getMemberHiSentence())
                .memberGuideInterest(member.getMemberGuideInterest())
                .memberId(member.getId())
                .memberProfileOriginalName(member.getMemberProfileOriginalName())
                .memberProfilePath(member.getMemberProfilePath())
                .memberProfileUUID(member.getMemberProfileUUID())
                .memberName(member.getMemberName())
                .memberPhone(member.getMemberPhone())
                .memberRole(member.getMemberRole())
                .memberSleep(member.getMemberSleep())
                .memberRole(member.getMemberRole())
                .events(
                        member.getEvents().stream().map(this::eventToDTO).collect(Collectors.toList()))
                .reviews(
                        member.getReviews().stream().map(this::ReviewToDTO).collect(Collectors.toList()))
                .build();
    }

}
