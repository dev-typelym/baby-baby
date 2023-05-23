package com.app.babybaby.domain.boardDTO.askDTO;

import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.ProcessType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@NoArgsConstructor
public class AskDTO {
    private Long id;
    private ProcessType askStatus;
    private String askBoardTitle;
    private String askBoardContent;
    private Member member;
    private LocalDateTime registerDate;
    private String answerTitle;
    private String answerContent;


    @Builder
    public AskDTO(Long id, ProcessType askStatus, String askBoardTitle, String askBoardContent, Member member, LocalDateTime registerDate, String answerTitle, String answerContent) {
        this.id = id;
        this.askStatus = askStatus;
        this.askBoardTitle = askBoardTitle;
        this.askBoardContent = askBoardContent;
        this.member = member;
        this.registerDate = registerDate;
        this.answerTitle = answerTitle;
        this.answerContent = answerContent;
    }
}
