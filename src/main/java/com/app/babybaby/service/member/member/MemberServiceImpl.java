package com.app.babybaby.service.member.member;

import com.app.babybaby.controller.provider.UserDetail;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.memberDTO.CompanyDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.repository.board.review.ReviewRepository;
import com.app.babybaby.repository.member.follow.FollowRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.service.board.parentsBoard.ParentsBoardService;
import com.app.babybaby.service.board.review.ReviewService;
import com.app.babybaby.type.MemberType;
import com.app.babybaby.type.Role;
import com.app.babybaby.type.SleepType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("member") @Primary
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final ReviewService reviewService;

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;

    private final EventRepository eventRepository;

    private final FollowRepository followRepository;

    private final ParentsBoardService parentsBoardService;

    private final ParentsBoardRepository parentsBoardRepository;

    @Override
    public Optional<Member> getMemberById(Long memberId) {
        return memberRepository.findById(memberId);

    }
//    [회원 상세] 회사의 정보 가져오기
    @Override
    public CompanyDTO getAllCompanyInfo(Long companyId) {
       Member member = memberRepository.findById(companyId).get();
       CompanyDTO companyDTO = toCompanyDTO(member);
        List<ReviewDTO> reviews = companyDTO.getEvents().stream()
                .flatMap(eventDTO -> reviewRepository.findAllReivewByEventId(eventDTO.getId()).stream())
                .map(reviewService::ReviewToDTO)
                .collect(Collectors.toList());
        companyDTO.setReviews(reviews);
       return companyDTO;
    }
    
//    [회원 상세] 유저의 회사타입이 아닌 일반 유저 상세페이지
    public MemberDTO getAllUserInfo(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        MemberDTO memberDTO = toMemberDTO(member);
        memberDTO.setFollowerCount(followRepository.findFollowerMemberCountByMemberId_QueryDSL(memberId));
        memberDTO.setFollowingCount(followRepository.findFollowerMemberCountByMemberId_QueryDSL(memberId));

        memberDTO.setParentsBoards(
                followRepository.findAllParentsBoardByMemberId_QueryDSL(memberId).stream().map(parentsBoardService::toParentsBoardDTO).collect(Collectors.toList())
        );
        memberDTO.setReviews(
                followRepository.findALlReviewByMemberId_QueryDSL(memberId).stream().map(reviewService::ReviewToDTO).collect(Collectors.toList())
        );
        return memberDTO;
    }

    @Override
    public void joinGeneral(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberRole(Role.GENERAL);
        memberDTO.setMemberType(MemberType.GENERAL);
        memberDTO.setMemberRegisterDate(LocalDateTime.now());
        memberRepository.save(memberDTOToEntity(memberDTO));
    }

    @Override
    public void joinCompany(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberRole(Role.COMPANY);
        memberDTO.setMemberType(MemberType.COMPANY);
        memberDTO.setMemberRegisterDate(LocalDateTime.now());
        memberDTO.setMemberNickname(memberDTO.getMemberName());
        memberRepository.save(memberDTOToEntity(memberDTO));
    }

    @Override
    public Long overlapByMemberEmail(String memberEmail) {
        return (memberRepository.overlapByMemberEmail_QueryDSL(memberEmail));
    }

    @Override
    public Long overlapByPhone(String memberPhone) {
        return (memberRepository.overlapByPhone_QueryDSL(memberPhone));
    }

    @Override
    public Long overlapByMemberNickname(String memberNickname) {
        return (memberRepository.overlapByMemberNickname_QueryDSL(memberNickname));
    }

    @Override
    public Member findByMemberEmail(String memberEmail) {
        return (memberRepository.findByMemberEmail_QueryDSL(memberEmail));
    }

    @Override
    public void updatePassword(Long id, String memberPassword) {

    }

    @Override
    public void updateMemberStatus(Long id, SleepType memberSleep) {

    }

    @Override
    public void setMemberInfoMyId(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberRepository.setMemberInfoMyId(memberDTOToEntity(memberDTO));
//        memberDTOToEntity --> 빌더 다 안 들어가 있을 수도 있으니까 확인하고 말해주기!!
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return UserDetail.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberRole(member.getMemberRole())

                .build();
    }




    public String getKaKaoAccessToken(String code, String type){
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";
//
//        try{
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//
//            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            StringBuilder sb = new StringBuilder();
//            sb.append("grant_type=authorization_code");
//            sb.append("&client_id=7d1b8d2a934aea81ad94f34bb0bee10c"); // TODO REST_API_KEY 입력
//
////            회원가입에서 접근했을 때
//            if(type.equals("join")) {
//                sb.append("&redirect_uri=http://localhost:10000/member/kakao"); // TODO 인가코드 받은 redirect_uri 입력
//            } else if (type.equals("login")) {
////            로그인에서 접근했을 때
//                sb.append("&redirect_uri=http://localhost:10000/member/kakao-login"); // TODO 인가코드 받은 redirect_uri 입력
//            }
//            sb.append("&code=" + code);
//            bw.write(sb.toString());
//            bw.flush();
//
//            //결과 코드가 200이라면 성공
//            int responseCode = conn.getResponseCode();
//            log.info("responseCode : " + responseCode);
//            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = "";
//            String result = "";
//
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//            log.info("response body : " + result);
//
//            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
//
//            access_Token = element.getAsJsonObject().get("access_token").getAsString();
//            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
//
//            log.info("access_token : " + access_Token);
//            log.info("refresh_token : " + refresh_Token);
//
//            br.close();
//            bw.close();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

        return access_Token;
    }

    @Override
    public List<Member> getMemberList(Long id) {
        return null;
    }


    /* 카카오 사용자 정보 불러오기 */
//    public Member getKakaoInfo(String token) throws Exception {
//
//        Member kakaoInfo = new Member();
//        String reqURL = "https://kapi.kakao.com/v2/user/me";
//
//        //access_token을 이용하여 사용자 정보 조회
//        try {
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송
//
//            //결과 코드가 200이라면 성공
//            int responseCode = conn.getResponseCode();
//            log.info("responseCode : " + responseCode);
//
//            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = "";
//            String result = "";
//
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//            log.info("response body : " + result);
//
//            //Gson 라이브러리로 JSON파싱
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
//
//            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//
//            int id = element.getAsJsonObject().get("id").getAsInt();
//            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
//            String userEmail = "";
//            if(hasEmail){
//                userEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
//            }
//
//            log.info("id : " + id);
//            log.info("email : " + userEmail);
//            kakaoInfo.setMemberEmail(userEmail);
//
//            br.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return kakaoInfo;
//    }
}
