package com.app.babybaby.service.admin.adminParentsBoard;

import com.app.babybaby.domain.adminDTO.AdminParentsBoardDTO;
import com.app.babybaby.domain.fileDTO.parentsBoardFileDTO.ParentsBoardFileDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.search.admin.AdminParentsBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface AdminParentsBoardService {

    //    관리자 부모님마당 목록
    public Page<AdminParentsBoardDTO> getAdminParentsBoardListWithPaging(int page, AdminParentsBoardSearch parentsBoardSearch);

    //    관리자 부모님마당 상세 보기
    public AdminParentsBoardDTO getAdminParentsBoardById(Long boardId);

    //    관리자 부모님마당 삭제하기
    public void deleteAdminParentsBoard(List<String> boardIds);

    default AdminParentsBoardDTO toAdminParentsBoardDTO(ParentsBoard parentsBoard){
        return AdminParentsBoardDTO.builder()
                .id(parentsBoard.getId())
                .eventTitle(parentsBoard.getEvent().getBoardTitle())
                .memberNickName(parentsBoard.getMember().getMemberName())
                .parensBoardTitle(parentsBoard.getBoardTitle())
                .parentsBoardContent(parentsBoard.getBoardContent())
                .parentsBoardRegisterDate(parentsBoard.getRegisterDate())
                .parentsBoardUpdateDate(parentsBoard.getUpdateDate())
                .parentsBoardFileDTOS(parentsBoard.getParentsBoardFiles().stream().map(parentsBoardFile -> parentsBoardFileToDTO(parentsBoardFile)).collect(Collectors.toList()))
                .build();
    }

    default ParentsBoardFileDTO parentsBoardFileToDTO(ParentsBoardFile parentsBoardFile){
        return ParentsBoardFileDTO.builder()
                .fileOriginalName(parentsBoardFile.getFileOriginalName())
                .filePath(parentsBoardFile.getFilePath())
                .fileUUID(parentsBoardFile.getFileUUID())
                .fileStatus(parentsBoardFile.getFileStatus())
                .build();
    }
}
