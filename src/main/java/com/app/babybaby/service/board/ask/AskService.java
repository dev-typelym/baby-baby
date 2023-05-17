package com.app.babybaby.service.board.ask;

import com.app.babybaby.domain.boardDTO.askDTO.AskDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.entity.board.ask.Ask;
import com.app.babybaby.search.admin.AdminAskSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.stream.Collectors;

public interface AskService {
    public Page<AskDTO> findAllAsk_queryDSL(Pageable pageable, AdminAskSearch adminAskSearch);
    public Optional<AskDTO> findAskById_queryDSL(Long askId);


    default AskDTO toAskDTO(Ask ask) {
        return AskDTO.builder()
                .id(ask.getId())
                .askStatus(ask.getAskStatus())
                .boardContent(ask.getBoardContent())
                .boardTitle(ask.getBoardTitle())
                .memberId(ask.getMember().getId())
                .registerDate(ask.getRegisterDate())
                .build();
    }

}
