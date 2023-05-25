package com.app.babybaby.repository.member.kid;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Kid;

import java.util.List;

public interface KidQueryDsl {
    /* 해당 이벤트의 아이들 정보 가져오기 */
    public List<Kid> findAllKidsByEventId_QueryDsl(Long eventId);
//    내 아이 전체 조회
    public List<Kid> findAllMyKid_QueryDsl(Long memberId);
//    내 아이 수 조회
    public Long findAllMyKidCount_QueryDsl(Long memberId);


}
