package com.app.babybaby.service.boardService.NowKids;


import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("nowKids") @Primary
public class NowKidsServiceImpl implements NowKidsService {

    private final NowKidsRepository nowKidsRepository;

    private final MemberRepository memberRepository;

    @Override
    public List<NowKidsDTO> getAllInfoForList() {
        List<NowKids> nowKidsList = nowKidsRepository.findAll();
        return nowKidsList.stream()
                .map(this::toNowKidsDTO)
                .collect(Collectors.toList());
    }
}
