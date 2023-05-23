package com.app.babybaby.service.board.ask;

import com.app.babybaby.domain.boardDTO.askDTO.AskDTO;
import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.entity.board.ask.Ask;
import com.app.babybaby.search.admin.AdminAskSearch;
import com.app.babybaby.type.ProcessType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;
import java.util.stream.Collectors;

public interface AskService{
    public Page<AskDTO> findAllAsk_queryDSL(Pageable pageable, AdminAskSearch adminAskSearch);
    public Optional<AskDTO> findAskById_queryDSL(Long askId);
    public Slice<AskDTO> findAllAskByMemberId(Long memberId,Pageable pageable,AdminAskSearch AdminAskSearch);


    default AskDTO toAskDTO(Ask ask) {
        AskDTO.AskDTOBuilder builder = AskDTO.builder()
                .id(ask.getId())
                .askStatus(ask.getAskStatus())
                .askBoardContent(ask.getBoardContent())
                .askBoardTitle(ask.getBoardTitle())
                .registerDate(ask.getRegisterDate());

        if (ask.getMember() != null) {
            builder.member(ask.getMember());
        }

        if (ask.getAskAnswer() != null) {
            builder.answerTitle(ask.getAskAnswer().getBoardTitle())
                    .answerContent(ask.getAskAnswer().getBoardContent());
        }

        return builder.build();
    }

    default Ask toAskEnity(AskDTO askDTO){
        return Ask.builder()
                .askStatus(ProcessType.HOLD)
                .member(askDTO.getMember())
                .boardTitle(askDTO.getAskBoardTitle())
                .boardContent(askDTO.getAskBoardContent())
                .build();
    }

    public Ask saveAskDTO(AskDTO askDTO);

}
