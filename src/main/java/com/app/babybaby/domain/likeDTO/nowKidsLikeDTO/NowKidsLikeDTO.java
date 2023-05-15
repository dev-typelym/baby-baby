package com.app.babybaby.domain.likeDTO.nowKidsLikeDTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class NowKidsLikeDTO {
    private Long nowKidsId;
    private Long memberId;

    @Builder
    public NowKidsLikeDTO(Long nowKidsId, Long memberId) {
        this.nowKidsId = nowKidsId;
        this.memberId = memberId;
    }
}
