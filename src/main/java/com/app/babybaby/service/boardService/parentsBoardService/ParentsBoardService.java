package com.app.babybaby.service.boardService.parentsBoardService;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.fileDTO.parentsBoardFileDTO.ParentsBoardFileDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ParentsBoardService {

//    게시글 상세보기
    public ParentsBoardDTO getParentsBoardDetail(Long id);

    //    게시글 목록 불러오기 DTO
    default ParentsBoardDTO toParentsBoardDTO(ParentsBoard parentsBoard) {
        return ParentsBoardDTO.builder()
                .id(parentsBoard.getId())
                .eventTitle(parentsBoard.getEvent().getBoardTitle())
                .eventCategory(parentsBoard.getEvent().getCategory())
                .parentsBoardContent(parentsBoard.getBoardContent())
                .parensBoardTitle(parentsBoard.getBoardTitle())
                .parentsBoardRegisterDate(parentsBoard.getRegisterDate())
                .parentsBoardUpdateDate(parentsBoard.getUpdateDate())
                .representFileOriginName(parentsBoard.getRepresentFileOrginName())
                .representFilePath(parentsBoard.getRepresentFilePath())
                .representFileUUID(parentsBoard.getRepresentFileUUID())
                .parentsBoardFileDTOS(parentsBoard.getParentsBoardFiles().stream()
                        .map(this::parentsBoardToDTO).collect(Collectors.toList()))
                .build();
    }

    default ParentsBoardFileDTO parentsBoardToDTO(ParentsBoardFile file) {
        return ParentsBoardFileDTO.builder()
                .fileOriginalName(file.getFileOriginalName())
                .filePath(file.getFilePath())
                .fileStatus(file.getFileStatus())
                .fileUUID(file.getFileUUID())
                .id(file.getId())
                .build();
    }

//    게시글 목록 불러오기
    public List<ParentsBoardDTO> getParentsBoardList();
}
