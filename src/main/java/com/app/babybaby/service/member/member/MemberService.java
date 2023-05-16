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
import com.app.babybaby.type.SleepType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface MemberService extends UserDetailsService {

    public Optional<Member> getMemberById(Long memberId);

    public CompanyDTO getAllMemberInfo(Long companyId);

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.DTOBuilder()
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

    //    회원가입
    public void joinGeneral(MemberDTO memberDTO, PasswordEncoder passwordEncoder);
    public void joinCompany(MemberDTO memberDTO, PasswordEncoder passwordEncoder);


    /* 이메일 중복 검사 */
    public Long overlapByMemberEmail_QueryDSL(String memberEmail);

    /* 휴대폰 중복 검사 */
    public Long overlapByPhone_QueryDSL(String memberPhone);

    /* 닉네임 중복 검사 */
    public Long overlapByMemberNickname_QueryDSL(String memberNickname);

    /* 비밀 번호, 이메일 찾기 */
    public Member findByMemberEmail_QueryDSL(String memberEmail);

    /* 비밀 번호 변경 */
    public void updatePassword_QueryDSL(Long id, String memberPassword);

    /* 회원 탈퇴 */
    public void updateMemberStatus_QueryDSL(Long id, SleepType memberSleep);

    // 회원 정보 수정
    public void setMemberInfoMyId_QueryDSL(MemberDTO memberDTO);


    /* 카카오 토큰 접근 */
    public String getKaKaoAccessToken(String code, String type);

    /* 카카오 사용자 정보 불러오기 */
//    public Member getKakaoInfo(String token) throws Exception;

    public List<Member> getMemberList(Long id);

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
                .memberProfilePath(memberDTO.getMemberFilePath())
                .memberRegisterDate(memberDTO.getMemberRegisterDate())
                .memberType(memberDTO.getMemberType())
                .memberRole(memberDTO.getMemberRole())
                .build();
    }

    default MemberDTO entityToMemberDTO(Member member){
        return MemberDTO.DTOBuilder()
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
                .memberProfilePath(member.getMemberFilePath())
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
