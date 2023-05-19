package com.app.babybaby.repository.like.nowKidsLike;


import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface NowKidsLikeQueryDsl {
    /* save시 member는 해당 세션의 아이디로 가져오기 */
    public List<NowKidsLike> findAllNowKidsLikeByMemberId_QueryDsl(Long sessionId);

    public boolean hasMemberLikedNowKids(Long memberId, Long nowKidsId);

    //    회원이 좋아요 누른 이벤트 게시글 조회
    public Slice<NowKids> findAllByMemberLikesWithPaging_QueryDSL(Pageable pageable, Long memberId);
}
