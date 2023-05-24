package com.app.babybaby.service.board.parentsBoard;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.exception.BoardNotFoundException;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.repository.file.parentsBoardFile.ParentsBoardFileRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.FileType;
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

    private final ParentsBoardFileRepository parentsBoardFileRepository;

    private final MemberRepository memberRepository;

    private final EventRepository eventRepository;

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
    public List<Event> getFindByEventId(Long id) {
        List<Event> events = eventRepository.findAllPurchasedEvents(id);
        return events;
    }
    /* 작성쪽 맴버 가져오기 */
    public Member getUserInfo(Long memberId){
        return memberRepository.findById(memberId).get();
    }

    //    내가쓴 게시글
    @Override
    public Page<ParentsBoardDTO> getFindParentBoardListByMemberId(Pageable pageable, Long memberId) {
        Page<ParentsBoard> boards = parentsBoardRepository.findParentBoardListByMemberId(pageable, memberId);
        List<ParentsBoardDTO> parentsBoardDTOS = boards.stream().map(parentsBoard -> toParentsBoardDTO(parentsBoard)).collect(Collectors.toList());

        return new PageImpl<>(parentsBoardDTOS, pageable, boards.getTotalElements());
    }

    @Override
    public void saveAll(Long memberId, Long eventId, ParentsBoardDTO parentsBoardDTO) {
        Member member = memberRepository.findById(memberId).get();
        Event event = eventRepository.findById(eventId).get();
        log.info("parentBoard는 " + parentsBoardDTO.toString());

        ParentsBoard parentsBoard = this.toParentsBoardDTOEntity(parentsBoardDTO);
        parentsBoard.setEvent(event);
        parentsBoard.setMember(member);

        ParentsBoard savedParentsBoard = parentsBoardRepository.save(parentsBoard);
        parentsBoardDTO.getParentsBoardFileDTOS().forEach(parentsBoardFileDTO -> {
            ParentsBoardFile parentsBoardFile = ParentsBoardFile.builder()
                    .fileOriginalName(parentsBoardFileDTO.getFileOriginalName())
                    .filePath(parentsBoardFileDTO.getFilePath())
                    .fileStatus(parentsBoardFileDTO.getFileStatus())
                    .fileUUID(parentsBoardFileDTO.getFileUUID())
                    .parentsBoard(savedParentsBoard) // Set the association with ParentsBoard
                    .build();

            parentsBoardFileRepository.save(parentsBoardFile);
        });
    }

    //    대표사진 파일 업로드
    @Override
    public void save(ParentsBoardDTO parentsBoardDTO) {
        parentsBoardRepository.save(toParentsBoardDTOEntity(parentsBoardDTO));
    }

//    상세보기 카테고리 최신글 2개 가져오기
    @Override
    public List<ParentsBoardDTO> find2RecentDesc(CategoryType categoryType) {
        List<ParentsBoard> parents = parentsBoardRepository.find2RecentDesc(categoryType);
        log.info(parents.toString());
        List<ParentsBoardDTO> parentsList = parents.stream().map(parentsBoard -> toParentsBoardDTO(parentsBoard)).collect(Collectors.toList());
        return parentsList;
    }

    @Override
    public List<ParentsBoard> find5RecentDesc() {
        List<ParentsBoard> parents = parentsBoardRepository.find5RecentDesc();
        log.info(parents.toString());
        List<ParentsBoard> parentsList = parents.stream()
                .collect(Collectors.toList());
        return parentsList;
    }


}