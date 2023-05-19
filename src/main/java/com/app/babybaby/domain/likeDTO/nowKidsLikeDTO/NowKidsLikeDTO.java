package com.app.babybaby.domain.likeDTO.nowKidsLikeDTO;


import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
@NoArgsConstructor
public class NowKidsLikeDTO {
    private Long id;
    private String memberName;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private Long nowKidsId;
    private String address;
    private String addressDetail;
    private String addressSubDetail;
    private String postcode;
    private Long memberId;
    private List<NowKidsFileDTO> nowKidsFileDTOS;

    @Builder
    public NowKidsLikeDTO(Long id, String memberName, String boardTitle, String boardContent, LocalDateTime registerDate, LocalDateTime updateDate, Long nowKidsId, String address, String addressDetail, String addressSubDetail, String postcode, Long memberId, List<NowKidsFileDTO> nowKidsFileDTOS) {
        this.id = id;
        this.memberName = memberName;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.nowKidsId = nowKidsId;
        this.address = address;
        this.addressDetail = addressDetail;
        this.addressSubDetail = addressSubDetail;
        this.postcode = postcode;
        this.memberId = memberId;
        this.nowKidsFileDTOS = nowKidsFileDTOS;
    }
}
