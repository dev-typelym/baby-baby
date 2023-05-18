package com.app.babybaby.domain.boardDTO.askDTO;

import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.ProcessType;
import com.sun.istack.NotNull;
import lombok.Builder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

public class AskDTO {
    private Long id;
    private ProcessType askStatus;
    private String boardTitle;
    private String boardContent;
    private Long memberId;
    private LocalDateTime registerDate;


    @Builder
    public AskDTO(Long id,ProcessType askStatus, String boardTitle, String boardContent, Long memberId, LocalDateTime registerDate) {
        this.id = id;
        this.askStatus = askStatus;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.memberId = memberId;
        this.registerDate = registerDate;
    }
}
