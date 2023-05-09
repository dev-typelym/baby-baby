package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.user.Kid;
import com.app.babybaby.entity.user.User;

import java.time.LocalDateTime;
import java.util.List;

public interface NowKidsQueryDsl {
    /* 통솔자가 가지고있는 행사 가져오기 */
    public NowKids findNowKidsByGuideId_QueryDsl(Long guideId);

    /* NowKids 리스트를 위한 전체 솔자의 정보 */
    public List<User> findGuideBoard_QueryDsl();

    /* NowKids 리스트를 위한 전체 솔자의 정보 + 페이징 처리 */
    public List<User> findGuideBoardWithPaging_QueryDsl(int pageNo, int pageSize);

    /* 통솔자의 아이디로 통솔자가 신청한 모든 이벤트 가져오기 */
    public List<Event> findEventInfoByGuideId_QueryDsl(Long guideId);

    /* GeneralGuide의 아이디로 참여자 목록 가져오기 */
    public List<Kid> findAllKidsByGeneralGuideId_QueryDsl(Long sessionId);

}
