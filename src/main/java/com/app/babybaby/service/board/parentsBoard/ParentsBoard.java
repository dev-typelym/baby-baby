package com.app.babybaby.service.board.parentsBoard;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.fileDTO.parentsBoardFileDTO.ParentsBoardFileDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

public interface ParentsBoard {

    //    게시글 상세보기
    public ParentsBoardDTO getParentsBoardDetail(Long id);

    //    게시글 목록 불러오기
    public Page<ParentsBoardDTO> getFindAllWithSearchParentsBoardList(Pageable pageable, ParentsBoardSearch parentsBoardSearch);

    //    참여예정인 게시판 불러오기(작성쪽)
    public Event getFindByEventId(Long id);

    default ParentsBoardDTO toParentsBoardDTO(com.app.babybaby.entity.board.parentsBoard.ParentsBoard parentsBoard) {
        return ParentsBoardDTO.builder()
                .id(parentsBoard.getId())
                .eventTitle(parentsBoard.getEvent().getBoardTitle())
                .eventCategory(parentsBoard.getEvent().getCategory())
                .memberNickName((parentsBoard.getMember().getMemberNickname()))
                .parentsBoardContent(parentsBoard.getBoardContent())
                .parensBoardTitle(parentsBoard.getBoardTitle())
                .parentsBoardRegisterDate(parentsBoard.getRegisterDate())
                .parentsBoardUpdateDate(parentsBoard.getUpdateDate())
                .representFileOriginName(parentsBoard.getRepresentFileOrginName())
                .representFilePath(parentsBoard.getRepresentFilePath())
                .representFileUUID(parentsBoard.getRepresentFileUUID())
                .parentsBoardFileDTOS(parentsBoard.getParentsBoardFiles().stream()
                        .map(this::parentsBoardFileToDTO).collect(Collectors.toList()))
//                .parentsBoardReplyDTOS(parentsBoard.getParentsBoardReplies().stream()
//                        .map(this::parentsBoardReplyToDTO).collect(Collectors.toList()))
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

}