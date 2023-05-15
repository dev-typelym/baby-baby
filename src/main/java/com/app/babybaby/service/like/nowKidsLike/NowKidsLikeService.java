package com.app.babybaby.service.like.nowKidsLike;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.likeDTO.nowKidsLikeDTO.NowKidsLikeDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;

public interface NowKidsLikeService {

    public void likeSave(Long nowKidsId, Long memberId);

    public void deleteLike(Long nowKidsId, Long memberId);

    default NowKidsLikeDTO toNowKidsLikeDTO(NowKidsLike nowKidsLike){
        return NowKidsLikeDTO.builder()
                .memberId(nowKidsLike.getMember().getId())
                .nowKidsId(nowKidsLike.getNowKids().getId())
                .build();
    }

}
