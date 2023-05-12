package com.app.babybaby.service.replyService.parentsBoardReplyService;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.stream.Collectors;

public interface ParentsBoardReplyService {

    public Slice<ParentsBoardReplyDTO> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id);

    default ParentsBoardReplyDTO ParentsBoardReplyToDTO(ParentsBoardReply parentsBoardReply) {
        return ParentsBoardReplyDTO.builder()
                .id(parentsBoardReply.getId())
                .registerDate(parentsBoardReply.getRegisterDate())
                .updateDate(parentsBoardReply.getUpdateDate())
                .parentsBoardReplyContent(parentsBoardReply.getParentsBoardReplyContent())
                .memberId(parentsBoardReply.getMember().getId())
                .memberNickName(parentsBoardReply.getMember().getMemberNickname())
                .memberFileOriginalName(parentsBoardReply.getMember().getMemberFileOriginalName())
                .memberFilePath(parentsBoardReply.getMember().getMemberFilePath())
                .memberFileUUID(parentsBoardReply.getMember().getMemberFileUUID())
                .parentsBoardId(parentsBoardReply.getParentsBoard().getId())
                .build();
    }

}
