package com.app.babybaby.service.board.nowKids;


import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.like.nowKidsLike.NowKidsLikeRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("nowKids") @Primary
@Slf4j
public class NowKidsServiceImpl implements NowKidsService {

    private final NowKidsRepository nowKidsRepository;

    private final NowKidsLikeRepository nowKidsLikeRepository;

    private final MemberRepository memberRepository;

    @Override
    /* 1페이지부터 시작, 모든 정보는 최신순 */
    public Page<NowKidsDTO> getAllInfoForListDesc(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("id").descending());
        Page<NowKids> nowKidsPage = nowKidsRepository.findAll(pageable);

        if (!nowKidsPage.hasNext()) {
            return Page.empty(); // 빈 페이지 반환
        }

        Page<NowKidsDTO> nowKidsDTOPage = nowKidsPage.map(this::toNowKidsDTO);

        nowKidsDTOPage = nowKidsDTOPage.map(nowKidsDTO -> {
            boolean isLiked = nowKidsLikeRepository.hasMemberLikedNowKids(nowKidsDTO.getMemberId());
            nowKidsDTO.setIsLiked(isLiked);
            return nowKidsDTO;
        });

        return nowKidsDTOPage;
    }


    public List<NowKidsDTO> getBoardAndCalendarByGeneralGuideId(Long sessionId){
        List<NowKidsDTO> nowKidsDTOS = new ArrayList<>();
        List<Tuple> nowKidsEvents = nowKidsRepository.findEventAndCalendarInfoByGuideId_QueryDsl(sessionId);

        return nowKidsDTOS;
    }

}
