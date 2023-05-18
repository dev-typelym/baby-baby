package com.app.babybaby.repository.board.ask;

import com.app.babybaby.entity.board.ask.Ask;
import com.app.babybaby.search.admin.AdminAskSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface AskQueryDsl {
    //    [관리자] 문의 목록 조회
    public Page<Ask> findAllAsk_queryDSL(Pageable pageable, AdminAskSearch adminAskSearch);

    //    [관리자] 문의 상세보기
    public Optional<Ask> findAskById_queryDSL(Long askId);

    //    [관리자] 문의 삭제하기
    public void deleteAskByIds_queryDSL(List<Long> askIds);

    //    내가쓴 문의
    public Slice<Ask> findAllAskByMemberId(Long memberId,Pageable pageable,AdminAskSearch AdminAskSearch);

}
