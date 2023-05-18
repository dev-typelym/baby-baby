package com.app.babybaby.service.reply.parentsBoardReply;

import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ParentsBoardReplyService {

    public Page<ParentsBoardReplyDTO> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id);

    public void parentsBoardReplySave(Long SessionId, ParentsBoardReplyDTO parentsBoardReplyDTO, Long parentsBoardId);

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

    default ParentsBoardReply parentsBoardReplyToEntity(ParentsBoardReplyDTO parentsBoardReplyDTO) {
        return ParentsBoardReply.builder()
                .parentsBoardReplyContent(parentsBoardReplyDTO.getParentsBoardReplyContent())
                .build();
    }

}
