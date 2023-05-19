package com.app.babybaby.service.board.parentsBoard;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.exception.BoardNotFoundException;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
//@Qualifier("parentsBoard") @Primary
public class ParentsBoardServiceImpl implements ParentsBoardService {

    private final ParentsBoardRepository parentsBoardRepository;

    @Override
    public ParentsBoard findById(Long id) {
        return parentsBoardRepository.findById(id).get();
    }

    //    게시글 상세보기
    @Override
    public ParentsBoardDTO getParentsBoardDetail(Long id) {
        com.app.babybaby.entity.board.parentsBoard.ParentsBoard parentsBoard = parentsBoardRepository.findDetailById_QueryDsl(id).orElseThrow(() -> {
            throw new BoardNotFoundException();
        });
        return toParentsBoardDTO(parentsBoard);
    }

    //    목록 불러오기
    @Override
    public Page<ParentsBoardDTO> getFindAllWithSearchParentsBoardList(Pageable pageable, ParentsBoardSearch parentsBoardSearch) {
        Page<com.app.babybaby.entity.board.parentsBoard.ParentsBoard> boards = parentsBoardRepository.findAllWithSearch_QueryDsl(pageable, parentsBoardSearch);
        List<ParentsBoardDTO> parentsBoardDTOS = boards.stream().map(parentsBoard -> toParentsBoardDTO(parentsBoard)).collect(Collectors.toList());

        return new PageImpl<>(parentsBoardDTOS, pageable, boards.getTotalElements());
    }

    //    작성 쪽 참여예정인 행상 불러오기
    @Override
    public Event getFindByEventId(Long id) {
        Event event = parentsBoardRepository.findByEventId_QueryDsl(id).get();
        return event;
    }

    //    내가쓴 게시글
    @Override
    public Page<ParentsBoardDTO> getFindParentBoardListByMemberId(Pageable pageable, Long memberId) {
        Page<ParentsBoard> boards = parentsBoardRepository.findParentBoardListByMemberId(pageable, memberId);
        List<ParentsBoardDTO> parentsBoardDTOS = boards.stream().map(parentsBoard -> toParentsBoardDTO(parentsBoard)).collect(Collectors.toList());

        return new PageImpl<>(parentsBoardDTOS, pageable, boards.getTotalElements());
    }

//    대표사진 파일 업로드
    @Override
    public void save(ParentsBoardDTO parentsBoardDTO) {
        parentsBoardRepository.save(toParentsBoardDTOEntity(parentsBoardDTO));
    }

//    상세보기 카테고리 최신글 2개 가져오기
    @Override
    public List<ParentsBoard> find2RecentDesc(CategoryType categoryType) {
        List<ParentsBoard> parents = parentsBoardRepository.find2RecentDesc(categoryType);
        log.info(parents.toString());
        List<ParentsBoard> parentsList = parents.stream()
                .collect(Collectors.toList());
        return parentsList;
    }


//        /* 최근 올린 8명 가져오기 */
//    public List<MemberDTO> find8AuthorDesc(){
//        List<Member> members = nowKidsRepository.find8AuthorDesc();
//        List<MemberDTO> memberDTOS = members.stream()
//                .map(memberService::toMemberDTO)
//                .collect(Collectors.toList());
//
//        return memberDTOS;
//    }

}