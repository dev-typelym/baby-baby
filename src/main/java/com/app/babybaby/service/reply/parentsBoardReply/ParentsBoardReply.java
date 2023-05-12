package com.app.babybaby.service.reply.parentsBoardReply;

import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ParentsBoardReply {

    public Slice<ParentsBoardReplyDTO> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id);

    default ParentsBoardReplyDTO ParentsBoardReplyToDTO(com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply parentsBoardReply) {
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
