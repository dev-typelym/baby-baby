package com.app.babybaby.repository.like.nowKidsLike;


import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;

import java.util.List;

public interface NowKidsLikeQueryDsl {
    /* save시 member는 해당 세션의 아이디로 가져오기 */
    public List<NowKidsLike> findAllNowKidsLikeByMemberId_QueryDsl(Long sessionId);

    public boolean hasMemberLikedNowKids(Long memberId, Long nowKidsId);


}
