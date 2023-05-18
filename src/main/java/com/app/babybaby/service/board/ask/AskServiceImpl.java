package com.app.babybaby.service.board.ask;

import com.app.babybaby.domain.boardDTO.askDTO.AskDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.entity.board.ask.Ask;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.repository.board.ask.AskRepository;
import com.app.babybaby.search.admin.AdminAskSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AskServiceImpl implements AskService {

    @Autowired
    private final AskRepository askRepository;

    
    @Override
    public Page<AskDTO> findAllAsk_queryDSL(Pageable pageable, AdminAskSearch adminAskSearch) {
        Page<Ask> asks = askRepository.findAllAsk_queryDSL(pageable, adminAskSearch);
        List<AskDTO> askDTOS = asks.stream().map(ask -> toAskDTO(ask)).collect(Collectors.toList());
        return new PageImpl<>(askDTOS,pageable,asks.getTotalElements());
    }




    @Override
    public Optional<AskDTO> findAskById_queryDSL(Long askId) {
        return Optional.empty();
    }
    
//  내가작성한 문의
    @Override
    public Slice<AskDTO> findAllAskByMemberId(Long memberId, Pageable pageable, AdminAskSearch adminAskSearch) {
        Slice<Ask> asks = askRepository.findAllAskByMemberId(memberId,pageable, adminAskSearch);
        List<AskDTO> collect = asks.get().map(ask -> toAskDTO(ask)).collect(Collectors.toList());

        return new SliceImpl<>(collect, pageable, asks.hasNext());
    }


}
