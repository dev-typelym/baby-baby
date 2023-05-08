package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.user.User;

import java.time.LocalDateTime;
import java.util.List;

public interface NowKidsQueryDsl {
    /* 통솔자가 가지고있는 행사 가져오기 */
    public List<NowKids> findNowKidsEventByDate();
    /* 방금 올린 회원의 정보 */
    /* 이 이벤트의 아이들 정보 */
    /* 사진 정보 */
}
