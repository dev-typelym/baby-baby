package com.app.babybaby.domain.likeDTO.nowKidsLikeDTO;


import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class NowKidsLikeDTO {
    private Long id;
    private Long nowKidsId;
    private Long memberId;
    private List<NowKidsFileDTO> nowKidsFileDTOS;

    @Builder

    public NowKidsLikeDTO(Long id,Long nowKidsId, Long memberId, List<NowKidsFileDTO> nowKidsFileDTOS) {
        this.id = id;
        this.nowKidsId = nowKidsId;
        this.memberId = memberId;
        this.nowKidsFileDTOS = nowKidsFileDTOS;
    }
}
