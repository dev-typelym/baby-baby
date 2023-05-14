package com.app.babybaby.service.board.parentsBoard;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.exception.BoardNotFoundException;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Qualifier("parentsBoard") @Primary
public class ParentsBoardServiceImpl implements ParentsBoardService {

    private final ParentsBoardRepository parentsBoardRepository;

    //    게시글 상세보기
    @Override
    public ParentsBoardDTO getParentsBoardDetail(Long id) {
        com.app.babybaby.entity.board.parentsBoard.ParentsBoard parentsBoard = parentsBoardRepository.findDetailById(id).orElseThrow(() -> {
            throw new BoardNotFoundException();
        });
        return toParentsBoardDTO(parentsBoard);
    }

    //    목록 불러오기
    @Override
    public Page<ParentsBoardDTO> getFindAllWithSearchParentsBoardList(Pageable pageable, ParentsBoardSearch parentsBoardSearch) {
        Page<com.app.babybaby.entity.board.parentsBoard.ParentsBoard> boards = parentsBoardRepository.findAllWithSearch(pageable, parentsBoardSearch);
        List<ParentsBoardDTO> parentsBoardDTOS = boards.stream().map(parentsBoard -> toParentsBoardDTO(parentsBoard)).collect(Collectors.toList());

        return new PageImpl<>(parentsBoardDTOS, pageable, boards.getTotalElements());
    }

    //    작성 쪽 참여예정인 행상 불러오기
    @Override
    public Event getFindByEventId(Long id) {
        Event event = parentsBoardRepository.findByEventId(id).get();
        return event;
    }

//    내가쓴 게시글
    @Override
    public Page<ParentsBoardDTO> getFindParentBoardListByMemberId(Pageable pageable, Long memberId) {
        Page<ParentsBoard> boards = parentsBoardRepository.findParentBoardListByMemberId(pageable, memberId);
        List<ParentsBoardDTO> parentsBoardDTOS = boards.stream().map(parentsBoard -> toParentsBoardDTO(parentsBoard)).collect(Collectors.toList());

        return new PageImpl<>(parentsBoardDTOS, pageable, boards.getTotalElements());
    }


}