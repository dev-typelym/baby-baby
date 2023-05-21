package com.app.babybaby.service.board.parentsBoard;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.fileDTO.parentsBoardFileDTO.ParentsBoardFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.type.CategoryType;
import org.apache.logging.log4j.message.ParameterizedNoReferenceMessageFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public interface ParentsBoardService {
    public ParentsBoard findById(Long id);


    //    게시글 상세보기
    public ParentsBoardDTO getParentsBoardDetail(Long id);

    //    게시글 목록 불러오기
    public Page<ParentsBoardDTO> getFindAllWithSearchParentsBoardList(Pageable pageable, ParentsBoardSearch parentsBoardSearch);

    //    참여예정인 게시판 불러오기(작성쪽)
    public Event getFindByEventId(Long id);
    //    내가쓴 게시글
    public Page<ParentsBoardDTO> getFindParentBoardListByMemberId(Pageable pageable,Long memberId);

    public void saveAll(Long memberId, Long eventId, ParentsBoardDTO parentsBoardDTO);

//    파일 업로드 저장
    public void save(ParentsBoardDTO parentsBoardDTO);

//    상세보기 카테고리 최신글 2개 가져오기
    public List<ParentsBoardDTO> find2RecentDesc(CategoryType categoryType);

    default ParentsBoardDTO toParentsBoardDTO(ParentsBoard parentsBoard) {
        return ParentsBoardDTO.builder()
                .id(parentsBoard.getId())
                .eventTitle(parentsBoard.getEvent().getBoardTitle())
                .eventCategory(parentsBoard.getEvent().getCategory())
                .memberNickname(parentsBoard.getMember().getMemberNickname())
                .parentsBoardContent(parentsBoard.getBoardContent())
                .parentsBoardTitle(parentsBoard.getBoardTitle())
                .parentsBoardRegisterDate(parentsBoard.getRegisterDate())
                .parentsBoardUpdateDate(parentsBoard.getUpdateDate())
                .representFileOriginName(parentsBoard.getRepresentFileOrginName())
                .representFilePath(parentsBoard.getRepresentFilePath())
                .representFileUUID(parentsBoard.getRepresentFileUUID())
                .parentsBoardFileDTOS(parentsBoard.getParentsBoardFiles().stream()
                        .map(this::parentsBoardFileToDTO).collect(Collectors.toList()))
                .build();
    }


    default ParentsBoardFileDTO parentsBoardFileToDTO(ParentsBoardFile file) {
        return ParentsBoardFileDTO.builder()
                .fileOriginalName(file.getFileOriginalName())
                .filePath(file.getFilePath())
                .fileStatus(file.getFileStatus())
                .fileUUID(file.getFileUUID())
                .id(file.getId())
                .build();
    }

//    default ParentsBoardReplyDTO parentsBoardReplyToDTO(ParentsBoardReply reply) {
//        return ParentsBoardReplyDTO.builder()
//                .id(reply.getId())
//                .registerDate(reply.getRegisterDate())
//                .updateDate(reply.getUpdateDate())
//                .parentsBoardReplyContent(reply.getParentsBoardReplyContent())
//                .memberId(reply.getMember().getId())
//                .parentsBoardId(reply.getParentsBoard().getId())
//                .build();
//    }

//    파일 업로드용 빌더
    default ParentsBoard toParentsBoardDTOEntity(ParentsBoardDTO parentsBoardDTO) {
        return ParentsBoard.builder()
                .id(parentsBoardDTO.getId())
                .representFileUUID(parentsBoardDTO.getRepresentFileUUID())
                .representFileOrginName(parentsBoardDTO.getRepresentFileOriginName())
                .representFilePath(parentsBoardDTO.getRepresentFilePath())
                .boardContent(parentsBoardDTO.getParentsBoardContent())
                .boardTitle(parentsBoardDTO.getParentsBoardTitle())
                .build();
    }

}