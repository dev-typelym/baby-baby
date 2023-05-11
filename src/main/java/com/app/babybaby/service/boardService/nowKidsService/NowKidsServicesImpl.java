package com.app.babybaby.service.boardService.nowKidsService;


import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.like.nowKidsLike.NowKidsLikeRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
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
public class NowKidsServicesImpl implements NowKidsServices {

    private final NowKidsRepository nowKidsRepository;

    private final NowKidsLikeRepository nowKidsLikeRepository;

    private final MemberRepository memberRepository;

    @Override
    /* 1페이지부터 시작, 모든 정보는 최신순 */
    public Page<NowKidsDTO> getAllInfoForListDesc(int pageNum, int pageSize, Long sessionId) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize, Sort.by("id").descending());
        Page<NowKids> nowKidsPage = nowKidsRepository.findAll(pageable);
        Page<NowKidsDTO> nowKidsDTOPage = nowKidsPage.map(this::toNowKidsDTO);

        nowKidsDTOPage.forEach(nowKidsDTO -> {
            List<Kid> kids = nowKidsRepository.findAllKidsByEventIdAndGuideId_QueryDsl(nowKidsDTO.getMemberId(), nowKidsDTO.getEventId());
            nowKidsDTO.setKids(kids);
            nowKidsDTO.setNowKidsLikes(nowKidsLikeRepository.findAllNowKidsLikeByMemberId_QueryDsl(sessionId));
        });

        log.info(String.valueOf(pageNum));
        return nowKidsDTOPage;
    }

    public List<Event> getBoardAndCalendarByGeneralGuideId(Long sessionId){
        return nowKidsRepository.findEventInfoByGuideId_QueryDsl(sessionId);
    }

}
