package com.app.babybaby.service.boardService.nowKidsService;


import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("nowKids") @Primary
public class NowKidsServicesImpl implements NowKidsServices {

    private final NowKidsRepository nowKidsRepository;

    private final MemberRepository memberRepository;

    @Override
    public List<NowKidsDTO> getAllInfoForList() {
        List<NowKids> nowKidsList = nowKidsRepository.findAll();
        List<NowKidsDTO> nowKidsDTOList = nowKidsList.stream()
                .map(this::toNowKidsDTO)
                .collect(Collectors.toList());

        nowKidsDTOList.forEach(nowKidsDTO -> {
            List<Kid> kids = nowKidsRepository.findAllKidsByEventIdAndGuideId_QueryDsl(nowKidsDTO.getMemberId(), nowKidsDTO.getId());
            nowKidsDTO.setKids(kids);
        });

        return nowKidsDTOList;
    }
}
