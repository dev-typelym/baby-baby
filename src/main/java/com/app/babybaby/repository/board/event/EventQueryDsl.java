package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import com.app.babybaby.search.admin.AdminEventSearch;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface EventQueryDsl {
    //    이벤트 게시판 목록(무한스크롤)
    public Slice<Event> findEventListWithPaging_QueryDSL(/*EventBoardSearch eventBoardSearch,*/Pageable pageable);
    //    이벤트 게시판 상세 페이지
    public Optional<Event> findEventById_QueryDSL(Long id);
    //     결제 상세 페이지
    public Optional<Event> findEventPayById_QueryDSL(Long memberId, Long eventId);
//    기업 이벤트 게시글 조회
    public Page<Event> findEventListByCompanyId_QueryDSL(Pageable pageable, Long companyId);

//    내가 참여한 이벤트 게시판
    public Page<Purchase> findEventListByMemberId_QueryDSL(Pageable pageable, Long memberId);

    //   [관리자] 놀러가요 카테고리 및 상태별 게시글 목록 조회
    public List<Event> findNowKidsEvents_queryDSL(AdminEventSearch adminEventSearch, CategoryType eventCategory, String eventStatus);
    //   [관리자] 놀러가요 상세보기
    public Optional<Event> findForAdminEventById_queryDSL(Long eventId);
    //   [관리자] 놀러가요 삭제하기
    public void deleteEventByIds_queryDSL(List<Long> eventIds);
}
