package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.search.admin.AdminParentsBoardSearch;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ParentsBoardQueryDsl {
    //    전체 목록
    public Page<ParentsBoard> findAllWithSearch_QueryDsl(Pageable pageable, ParentsBoardSearch parentsBoardSearch);

//    내가쓴 게시글
    public Page<ParentsBoard> findParentBoardListByMemberId(Pageable pageable,Long memberId);

    //    상세보기
    public Optional<ParentsBoard> findDetailById_QueryDsl(Long id);

    //    작성하기(1단계)
    public List<Event> findAllUpcomingEventsByMemberId(Long id);

//    상세보기 카테고리 최신글 2개 가져오기
    public List<ParentsBoard> find2RecentDesc(CategoryType category);

//    작성하기(2단계 대표사진)

    public Page<ParentsBoard> findListByMemberIdWithPaging_QueryDSL(Pageable pageable,Long memberId);

    //    [관리자] 부모님마당 목록 조회
    public Page<ParentsBoard> findAllParentsBoardWithSearch_queryDSL(Pageable pageable, AdminParentsBoardSearch adminParentsBoardSearch);

    //    [관리자] 부모님마당 상세
    public Optional<ParentsBoard> findParentsBoardById_queryDSL(Long parentsId);

    //    [관리자] 부모님마당 삭제
    public void deleteAdminParentsBoardByIds_queryDSL(List<Long> parentsBoardIds);
}

