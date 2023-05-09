package com.app.babybaby.repository.user.kid;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.user.Kid;

import java.util.List;

public interface KidQueryDsl {
    /* 해당 이벤트의 아이들 정보 가져오기 */
    public List<Kid> findAllKidsByEventId_QueryDsl(Long eventId);
    
}
