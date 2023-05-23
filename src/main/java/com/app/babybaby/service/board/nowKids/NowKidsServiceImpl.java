package com.app.babybaby.service.board.nowKids;


import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.like.nowKidsLike.NowKidsLikeRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.service.member.member.MemberService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("nowKids") @Primary
@Slf4j
public class NowKidsServiceImpl implements NowKidsService {

    private final MemberService memberService;

    private final NowKidsRepository nowKidsRepository;

    private final NowKidsLikeRepository nowKidsLikeRepository;

    private final MemberRepository memberRepository;

    @Override
    /* 1페이지부터 시작, 모든 정보는 최신순 */
    public Page<NowKidsDTO> getAllInfoForListDesc(int pageNum, int pageSize, Long sessionID) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("updateDate").descending());
        Page<NowKids> nowKidsPage = nowKidsRepository.findAll(pageable);

        if (nowKidsPage.isEmpty()) {
            return Page.empty(); // 빈 페이지 반환
        }

        Page<NowKidsDTO> nowKidsDTOPage = nowKidsPage.map(this::toNowKidsDTO);
        if(sessionID != null){
            nowKidsDTOPage = nowKidsDTOPage.map(nowKidsDTO -> {
                boolean isLiked = nowKidsLikeRepository.hasMemberLikedNowKids(sessionID, nowKidsDTO.getNowKidsId());
                nowKidsDTO.setIsLiked(isLiked);
                return nowKidsDTO;
            });
        }

        return nowKidsDTOPage;
    }


    public List<NowKidsDTO> getBoardAndCalendarByGeneralGuideId(Long sessionId){
        List<NowKidsDTO> nowKidsDTOS = new ArrayList<>();
        List<Tuple> nowKidsEvents = nowKidsRepository.findEventAndCalendarInfoByGuideId_QueryDsl(sessionId);

        return nowKidsDTOS;
    }

    /* 최근 올린 8명 가져오기 */
    public List<MemberDTO> find8AuthorDesc(){
        List<Member> members = nowKidsRepository.find8AuthorDesc();
        List<MemberDTO> memberDTOS = members.stream()
                .map(memberService::toMemberDTO)
                .collect(Collectors.toList());

        return memberDTOS;
    }

    @Override
    public void saveCrewSave(Event event, Member member) {
        
    }

    @Override
    public List<NowKids> find5RecentDesc() {
        List<NowKids> nowKidz = nowKidsRepository.find5RecentDesc();
        log.info(nowKidz.toString());
        List<NowKids> nowKidsList = nowKidz.stream()
                .collect(Collectors.toList());
        return nowKidsList;
    }


}
