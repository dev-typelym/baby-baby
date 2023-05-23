package com.app.babybaby.service.member.member;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.CompanyDTO;
import com.app.babybaby.domain.memberDTO.MailDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.domain.memberDTO.MemberDetailDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.RandomKey;
import com.app.babybaby.service.board.event.EventService;
import com.app.babybaby.service.board.event.EventServiceImpl;
import com.app.babybaby.service.board.review.ReviewService;
import com.app.babybaby.service.calendar.CalendarService;
import com.app.babybaby.type.SleepType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface MemberService extends UserDetailsService {

    public Optional<Member> getMemberById(Long memberId);
    
//      [회원 상세] 회사 정보 가져오기
    public CompanyDTO getAllCompanyInfo(Long companyId);
//    회원상세 ajax로 이벤트들 들고오기
public CompanyDTO getEventsInfoByMemberId(Long companyId, Pageable pageable);

//      [회원상세] ajax로 리뷰 페이징 처리
    public CompanyDTO getAllReviewInfoByMemberId(Long companyId, Pageable pageable);

//    회원 상세 ajax로 페이징 처리
    public MemberDetailDTO getAllGeneralMemberInfo(Long memberId, Pageable pageable);

    public MemberDTO getAllUserInfo(Long memberId, Long sessionId);

    public MemberDTO getUserInfoForPurchase(Long memberId, Long eventId);

    public void save(MemberDTO memberDTO,Long memberId);

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.dtoBuilder()
                .id(member.getId())
                .memberSleep(member.getMemberSleep())
                .memberHiSentence(member.getMemberHiSentence())
                .memberNickname(member.getMemberNickname())
                .memberAddress(member.getMemberAddress())
                .memberEmail(member.getMemberEmail())
                .memberNickname(member.getMemberNickname())
                .memberProfileUUID(member.getMemberProfileUUID())
                .memberProfilePath(member.getMemberProfilePath())
                .memberProfileOriginalName(member.getMemberProfileOriginalName())
                .build();
    }

    default CalendarDTO toCalendarDTO(Calendar calendar){
        return CalendarDTO.builder()
                .calendarName(calendar.getCalendarName())
                .endDate(calendar.getEndDate())
                .startDate(calendar.getStartDate())
                .id(calendar.getId())
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
                .calendar(toCalendarDTO(event.getCalendar()))
                .files(event.getEventFiles().stream().map(this::toEventFileDTO).collect(Collectors.toList()))
                .build();
    }

    default EventFileDTO toEventFileDTO(EventFile eventFile){
        return EventFileDTO.builder()
                .fileOriginalName(eventFile.getFileOriginalName())
                .filePath(eventFile.getFilePath())
                .fileUUID(eventFile.getFileUUID())
                .fileStatus(eventFile.getFileStatus())
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
                .memberPhone(member.getMemberPhone())
                .memberNickname(member.getMemberNickname())
                .memberAddress(member.getMemberAddress())
                .memberEmail(member.getMemberEmail())
                .memberGuideStatus(member.getMemberGuideStatus())
                .memberHiSentence(member.getMemberHiSentence())
                .memberId(member.getId())
                .memberProfileOriginalName(member.getMemberProfileOriginalName())
                .memberProfilePath(member.getMemberProfilePath())
                .memberProfileUUID(member.getMemberProfileUUID())
                .memberName(member.getMemberName())
                .memberPhone(member.getMemberPhone())
                .memberType(member.getMemberType())
                .memberSleep(member.getMemberSleep())
                .memberRole(member.getMemberRole())
                .events(
                        member.getEvents().stream().map(this::eventToDTO).collect(Collectors.toList()))
                .reviews(
                        member.getReviews().stream().map(this::ReviewToDTO).collect(Collectors.toList()))
                .build();
    }

    //    회원가입
    public void joinGeneral(MemberDTO memberDTO, PasswordEncoder passwordEncoder);
    public void joinCompany(MemberDTO memberDTO, PasswordEncoder passwordEncoder);


    /* 이메일 중복 검사 */
    public Long overlapByMemberEmail(String memberEmail);

    /* 휴대폰 중복 검사 */
    public Long overlapByPhone(String memberPhone);

    /* 닉네임 중복 검사 */
    public Long overlapByMemberNickname(String memberNickname);

    /* 비밀 번호, 이메일 찾기 */
    public Member findByMemberEmail(String memberEmail);

    /* 비밀 번호 변경 */
    public void updatePassword(Long id, String memberPassword, PasswordEncoder passwordEncoder);

    /* 회원 탈퇴 */
    public void updateMemberStatus(Long id, SleepType memberSleep);

    // 회원 정보 수정
    public void setMemberInfoById(MemberDTO memberDTO, PasswordEncoder passwordEncoder);

//    회원정보 수정 (정표 var.)
    public void setInfoMemberById(MemberDTO memberDTO,PasswordEncoder passwordEncoder);

//    정표
    public MemberDTO findByMemberId(Long memberId);

    /* 랜덤키로 계정 찾기 */
    public Member findMemberByRandomKey(String randomKey);

    /* 랜덤키로 계정 찾기 */
    public Member findMemberByMemberEmailAndRandomKey(String memberEmail, String randomKey);

    /* 메일보내기 */
    public void sendMail(MailDTO mail);

    default Member memberDTOToEntity(MemberDTO memberDTO) {
        return Member.joinMemberBuilder()
                .id(memberDTO.getId())
                .memberName(memberDTO.getMemberName())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberNickname(memberDTO.getMemberNickname())
                .memberPhone(memberDTO.getMemberPhone())
                .memberAddress(memberDTO.getMemberAddress())
                .memberProfileOriginalName(memberDTO.getMemberProfileOriginalName())
                .memberProfilePath(memberDTO.getMemberProfilePath())
                .memberProfileUUID(memberDTO.getMemberProfileUUID())
                .memberRegisterDate(memberDTO.getMemberRegisterDate())
                .memberType(memberDTO.getMemberType())
                .memberRole(memberDTO.getMemberRole())
                .build();
    }

    default MemberDTO entityToMemberDTO(Member member){
        return MemberDTO.dtoBuilder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberNickname(member.getMemberNickname())
                .memberHiSentence(member.getMemberHiSentence())
                .memberPhone(member.getMemberPhone())
                .memberAddress(member.getMemberAddress())
                .memberProfileOriginalName(member.getMemberProfileOriginalName())
                .memberProfileUUID(member.getMemberProfileUUID())
                .memberProfilePath(member.getMemberProfilePath())
                .memberRegisterDate(member.getMemberRegisterDate())
                .memberType(member.getMemberType())
                .memberRole(member.getMemberRole())
                .memberGuideStatus(member.getMemberGuideStatus())
                .memberSleep(member.getMemberSleep())
                .memberGuideType(member.getMemberGuideType())
                .memberGuideInterest(member.getMemberGuideInterest())
                .memberFilePath(member.getMemberFilePath())
                .memberFileOriginalName(member.getMemberFileOriginalName())
                .memberFileUUID(member.getMemberFileUUID())
                .build();
    }

}
