package com.app.babybaby.domain.likeDTO.eventLikeDTO;

import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
@RequiredArgsConstructor
public class EventLikeDTO {
    private Long id;
    private String boardTitle;
    private Long eventPrice;
    private Long memberId;
    private String memberName;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private Address address;
    private Long eventRecruitCount;
    private List<EventFileDTO> eventFileDTOS;

    @Builder
    public EventLikeDTO(Long id, String boardTitle, Long eventPrice, Long memberId, String memberName, LocalDateTime registerDate, LocalDateTime updateDate, Long eventRecruitCount, List<EventFileDTO> eventFileDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.eventPrice = eventPrice;
        this.memberId = memberId;
        this.memberName = memberName;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.eventRecruitCount = eventRecruitCount;
        this.eventFileDTOS = eventFileDTOS;
    }
}
