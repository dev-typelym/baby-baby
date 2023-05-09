package com.app.babybaby.repository.board.nowKids;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.file.File;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.file.nowKidsFile.QNowKidsFile;
import com.app.babybaby.entity.member.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.board.nowKids.QNowKids.nowKids;
import static com.app.babybaby.entity.file.nowKidsFile.QNowKidsFile.nowKidsFile;
import static com.app.babybaby.entity.member.QCrew.crew;
import static com.app.babybaby.entity.member.QGuide.guide;
import static com.app.babybaby.entity.member.QKid.kid;

@RequiredArgsConstructor
public class NowKidsQueryDslImpl implements NowKidsQueryDsl {
    private final JPAQueryFactory query;
    /* 통솔자의 아이디로 통솔자(User)의 모든 정보 가져오기 */
    public NowKids findNowKidsByGuideId_QueryDsl(Long guideId) {
        return query
                .selectFrom(nowKids)
                .join(nowKids.guide)
                .where(nowKids.guide.id.eq(guideId))
                .fetchOne();
    }
    
    /* 통솔자의 아이디로 그 사람이 통솔중인 모든 정보 가져오기 */
    /* GeneralGuide의 아이디로 그 사람이 통솔중인 이벤트 정보 가져오기 */
    public List<Event> findEventInfoByGuideId_QueryDsl(Long generalGuideId) {
        return query.select(event)
                .from(guide)
                .orderBy(event.id.desc())
                .join(guide.event, event)
                .where(guide.generalGuide.id.eq(generalGuideId))
                .fetch();
    }

    /* 세션에 있는 아이디로 참여자 목록 가져오기 */
    public List<Kid> findAllKidsByGeneralGuideId_QueryDsl(Long sessionId) {
        return query.select(kid)
                .from(guide)
                .join(guide.crews, crew)
                .join(crew.kid, kid)
                .where((guide.generalGuide.id.eq(sessionId)).or((guide.adminGuide.id).eq(sessionId)))
                .fetch();
    }


    /* 통솔자의 모든 정보(User)만 뿌려주기 */
    public List<Member> findGuideBoard_QueryDsl() {
        return query.select(nowKids)
                .from(nowKids)
                .join(nowKids.guide)
                .fetchJoin()
                .fetch()
                .stream()
                .map(NowKids::getGuide)
                .collect(Collectors.toList());
    }
    
/* pageNo는 0부터 시작 */
    public List<Member> findGuideBoardWithPaging_QueryDsl(int pageNo, int pageSize) {
        return query.selectFrom(nowKids)
                .orderBy(nowKids.id.desc())
                .join(nowKids.guide)
                .fetchJoin()
                .offset(pageNo * pageSize)
                .limit(pageSize)
                .fetch()
                .stream()
                .map(NowKids::getGuide)
                .collect(Collectors.toList());
    }

    /* 해당 보드의 모든 파일 가져오기  수정필요*/
    public List<NowKidsFile> findAllFileNowKidsById_QueryDsl(Long nowKidsId){
        return query.selectFrom(nowKidsFile)
                .where(nowKidsFile.nowKids.id.eq(nowKidsId))
                .orderBy(nowKidsFile.id.desc())
                .fetch();
    }
    
    /* 한방쿼리 실패 */
//    public List<NowKids> findAllInfo(){
//        return query.select(nowKids)
//                .from(nowKids)
//                .orderBy(nowKids.id.desc())
//                .join(nowKids.guide).fetchJoin()
//                .join(nowKids.event).fetchJoin()
//                .fetch();
//
//    }


}