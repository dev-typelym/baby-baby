package com.app.babybaby.service.like.nowKidsLike;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.domain.likeDTO.eventLikeDTO.EventLikeDTO;
import com.app.babybaby.domain.likeDTO.nowKidsLikeDTO.NowKidsLikeDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.stream.Collectors;

public interface NowKidsLikeService {

    public Slice<NowKidsLikeDTO> findEventLikeByMemberId(Pageable pageable, Long memberId);

    public void likeSave(Long nowKidsId, Long memberId);

    public void deleteLike(Long nowKidsId, Long memberId);

    default NowKidsLikeDTO toNowKidsLikeDTO(NowKids nowKids){
        return NowKidsLikeDTO.builder()
                .memberId(nowKids.getEvent().getCompany().getId())
                .nowKidsId(nowKids.getId())
                .nowKidsFileDTOS(nowKids.getNowKidsFile().stream().map(nowKidsFile -> toNowKidsFileDTO(nowKidsFile)).collect(Collectors.toList()))
                .build();
    }

    default NowKidsFileDTO toNowKidsFileDTO(NowKidsFile nowKidsFile){
        return NowKidsFileDTO.builder()
                .fileOriginalName(nowKidsFile.getFileOriginalName())
                .fileUUID(nowKidsFile.getFileUUID())
                .filePath(nowKidsFile.getFilePath())
                .build();
    }

}
