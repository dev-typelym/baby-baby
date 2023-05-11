package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;

import java.time.LocalDateTime;
import java.util.List;

public interface NowKidsQueryDsl {
    /* 통솔자가 가지고있는 행사 가져오기 */
    public NowKids findNowKidsByGuideId_QueryDsl(Long guideId);

    /* NowKids 리스트를 위한 전체 통솔자의 정보 */
    public List<Member> findGuideBoard_QueryDsl();

    /* NowKids 리스트를 위한 전체 솔자의 정보 + 페이징 처리 */
    public List<Member> findGuideBoardWithPaging_QueryDsl(int pageNo, int pageSize);

    /* 통솔자의 아이디로 통솔자가 신청한 모든 이벤트 가져오기 */
    public List<Event> findEventInfoByGuideId_QueryDsl(Long guideId);
    
    /* 만약 generalId가 없을시 GuideId로 조회 */
    public List<Event> findEventInfoByAdminGuideId_QueryDsl(Long adminGuideId);

    /* GeneralGuide의 아이디로 참여자 목록 가져오기 */
    public List<Kid> findAllKidsByGeneralGuideId_QueryDsl(Long sessionId);

    /* 해당 보드의 모든 파일 가져오기  수정필요*/
    public List<NowKidsFile> findAllFileNowKidsById_QueryDsl(Long nowKidsId);

    /* 해당 이벤트에 어떤 아이들이 참석했는지 */
    public List<Kid> findAllKidsByEventIdAndGuideId_QueryDsl(Long guideId, Long eventId);

    public LocalDateTime findUpdateTime_QueryDsl(Long nowKidsId);

    /*한방쿼리로 모든 정보 다 가져오기 */
//    public List<NowKids> findAllInfo();

}
